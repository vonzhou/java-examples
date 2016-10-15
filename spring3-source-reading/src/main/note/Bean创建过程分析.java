

public class Main{
    /**
     * Central method of this class: creates a bean instance,
     * populates the bean instance, applies post-processors, etc.
     * @see #doCreateBean
     */
    @Override
    protected Object createBean(final String beanName, final RootBeanDefinition mbd, final Object[] args)
            throws BeanCreationException {

        if (logger.isDebugEnabled()) {
            logger.debug("Creating instance of bean '" + beanName + "'");
        }
        // Make sure bean class is actually resolved at this point.
        resolveBeanClass(mbd, beanName);

        /**
         *  Prepare method overrides.
         *  验证要重写的方法
         */
        try {
            mbd.prepareMethodOverrides();
        }
        catch (BeanDefinitionValidationException ex) {
            throw new BeanDefinitionStoreException(mbd.getResourceDescription(),
                    beanName, "Validation of method overrides failed", ex);
        }

        try {
            /**
             * Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
             * 后处理器
             */
            Object bean = resolveBeforeInstantiation(beanName, mbd);
            if (bean != null) {
                return bean;
            }
        }
        catch (Throwable ex) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                    "BeanPostProcessor before instantiation of bean failed", ex);
        }
        /**
         * 常规流程到这里
         */
        Object beanInstance = doCreateBean(beanName, mbd, args);
        if (logger.isDebugEnabled()) {
            logger.debug("Finished creating instance of bean '" + beanName + "'");
        }
        return beanInstance;
    }

    /**
     * Actually create the specified bean. Pre-creation processing has already happened
     * at this point, e.g. checking <code>postProcessBeforeInstantiation</code> callbacks.
     * <p>Differentiates between default bean instantiation, use of a
     * factory method, and autowiring a constructor.
     * @param beanName the name of the bean
     * @param mbd the merged bean definition for the bean
     * @param args arguments to use if creating a prototype using explicit arguments to a
     * static factory method. This parameter must be <code>null</code> except in this case.
     * @return a new instance of the bean
     * @throws BeanCreationException if the bean could not be created
     * @see #instantiateBean
     * @see #instantiateUsingFactoryMethod
     * @see #autowireConstructor
     */
    protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final Object[] args) {
        // Instantiate the bean.
        BeanWrapper instanceWrapper = null;
        /**
         * this.factoryBeanInstanceCache 缓存的是未完成的 FactoryBean instances（FactoryBean name --> BeanWrapper）
         */
        if (mbd.isSingleton()) {
            instanceWrapper = this.factoryBeanInstanceCache.remove(beanName);
        }
        /**
         * 实例不存在则创建
         */
        if (instanceWrapper == null) {
            instanceWrapper = createBeanInstance(beanName, mbd, args);
        }
        final Object bean = (instanceWrapper != null ? instanceWrapper.getWrappedInstance() : null);
        Class beanType = (instanceWrapper != null ? instanceWrapper.getWrappedClass() : null);

        /**
         * 应用 MergedBeanDefinitionPostProcessors
         * Allow post-processors to modify the merged bean definition.
         */
        synchronized (mbd.postProcessingLock) {
            if (!mbd.postProcessed) {
                applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
                mbd.postProcessed = true;
            }
        }

        /**
         * 提前暴露
         * Eagerly cache singletons to be able to resolve circular references
         * even when triggered by lifecycle interfaces like BeanFactoryAware.
         */
        boolean earlySingletonExposure = (mbd.isSingleton() && this.allowCircularReferences &&
                isSingletonCurrentlyInCreation(beanName));
        if (earlySingletonExposure) {
            if (logger.isDebugEnabled()) {
                logger.debug("Eagerly caching bean '" + beanName +
                        "' to allow for resolving potential circular references");
            }
            addSingletonFactory(beanName, new ObjectFactory() {
                public Object getObject() throws BeansException {
                    return getEarlyBeanReference(beanName, mbd, bean);
                }
            });
        }

        /**
         * 填充bean实例：bean definition -> bean wrapper
         *  Initialize the bean instance.
         */
        Object exposedObject = bean;
        try {
            populateBean(beanName, mbd, instanceWrapper);
            if (exposedObject != null) {
                exposedObject = initializeBean(beanName, exposedObject, mbd);
            }
        }
        catch (Throwable ex) {
            if (ex instanceof BeanCreationException && beanName.equals(((BeanCreationException) ex).getBeanName())) {
                throw (BeanCreationException) ex;
            }
            else {
                throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Initialization of bean failed", ex);
            }
        }

        if (earlySingletonExposure) {
            Object earlySingletonReference = getSingleton(beanName, false);
            if (earlySingletonReference != null) {
                if (exposedObject == bean) {
                    exposedObject = earlySingletonReference;
                }
                else if (!this.allowRawInjectionDespiteWrapping && hasDependentBean(beanName)) {
                    String[] dependentBeans = getDependentBeans(beanName);
                    Set<String> actualDependentBeans = new LinkedHashSet<String>(dependentBeans.length);
                    for (String dependentBean : dependentBeans) {
                        if (!removeSingletonIfCreatedForTypeCheckOnly(dependentBean)) {
                            actualDependentBeans.add(dependentBean);
                        }
                    }
                    if (!actualDependentBeans.isEmpty()) {
                        throw new BeanCurrentlyInCreationException(beanName,
                                "Bean with name '" + beanName + "' has been injected into other beans [" +
                                        StringUtils.collectionToCommaDelimitedString(actualDependentBeans) +
                                        "] in its raw version as part of a circular reference, but has eventually been " +
                                        "wrapped. This means that said other beans do not use the final version of the " +
                                        "bean. This is often the result of over-eager type matching - consider using " +
                                        "'getBeanNamesOfType' with the 'allowEagerInit' flag turned off, for example.");
                    }
                }
            }
        }

        /**
         * 如果是单例 & 配置了 destroy-method 属性，则包装成 DisposableBeanAdapter 注册到一个hashmap中供销毁singleton的时候处理
         * Register bean as disposable.
         */
        try {
            registerDisposableBeanIfNecessary(beanName, bean, mbd);
        }
        catch (BeanDefinitionValidationException ex) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Invalid destruction signature", ex);
        }

        return exposedObject;
    }

    /**
     * 创建bean对应的实例，BeanDefinition -> BeanWrapper, 使用合适的实例化策略
     * Create a new instance for the specified bean, using an appropriate instantiation strategy:
     * factory method, constructor autowiring, or simple instantiation.
     * @param beanName the name of the bean
     * @param mbd the bean definition for the bean
     * @param args arguments to use if creating a prototype using explicit arguments to a
     * static factory method. It is invalid to use a non-null args value in any other case.
     * @return BeanWrapper for the new instance
     * @see #instantiateUsingFactoryMethod
     * @see #autowireConstructor
     * @see #instantiateBean
     */
    protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args) {
        // Make sure bean class is actually resolved at this point.
        Class beanClass = resolveBeanClass(mbd, beanName);

        if (beanClass != null && !Modifier.isPublic(beanClass.getModifiers()) && !mbd.isNonPublicAccessAllowed()) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                    "Bean class isn't public, and non-public access not allowed: " + beanClass.getName());
        }

        /**
         * 1. 有工厂方法（配置了 factory-method）则使用工厂方法
         */
        if (mbd.getFactoryMethodName() != null)  {
            return instantiateUsingFactoryMethod(beanName, mbd, args);
        }

        // Shortcut when re-creating the same bean...
        boolean resolved = false;
        boolean autowireNecessary = false;
        if (args == null) {
            synchronized (mbd.constructorArgumentLock) {
                /**
                 * resolvedConstructorOrFactoryMethod 缓存已然解析过的构造器和工厂方法，避免重复劳动
                 */
                if (mbd.resolvedConstructorOrFactoryMethod != null) {
                    resolved = true;
                    autowireNecessary = mbd.constructorArgumentsResolved;
                }
            }
        }
        if (resolved) {
            if (autowireNecessary) {
                return autowireConstructor(beanName, mbd, null, null);
            }
            else {
                return instantiateBean(beanName, mbd);
            }
        }

        /**
         *  Need to determine the constructor...
         * 2. 有多个构造函数，则根据参数抉择
         */
        Constructor[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
        if (ctors != null ||
                mbd.getResolvedAutowireMode() == RootBeanDefinition.AUTOWIRE_CONSTRUCTOR ||
                mbd.hasConstructorArgumentValues() || !ObjectUtils.isEmpty(args))  {
            return autowireConstructor(beanName, mbd, ctors, args);
        }

        /**
         * No special handling: simply use no-arg constructor.
         * 3. 使用默认无参构造器
         */
        return instantiateBean(beanName, mbd);
    }

    /**
     * "autowire constructor" (with constructor arguments by type) behavior.
     * Also applied if explicit constructor argument values are specified,
     * matching all remaining arguments with beans from the bean factory.
     * <p>This corresponds to constructor injection: In this mode, a Spring
     * bean factory is able to host components that expect constructor-based
     * dependency resolution.
     * @param beanName the name of the bean
     * @param mbd the bean definition for the bean
     * @param ctors the chosen candidate constructors
     * @param explicitArgs argument values passed in programmatically via the getBean method,
     * or <code>null</code> if none (-> use constructor argument values from bean definition)
     * @return BeanWrapper for the new instance
     */
    protected BeanWrapper autowireConstructor(
            String beanName, RootBeanDefinition mbd, Constructor[] ctors, Object[] explicitArgs) {

        return new ConstructorResolver(this).autowireConstructor(beanName, mbd, ctors, explicitArgs);
    }

    public BeanWrapper autowireConstructor(
            final String beanName, final RootBeanDefinition mbd, Constructor[] chosenCtors, final Object[] explicitArgs) {

        BeanWrapperImpl bw = new BeanWrapperImpl();
        this.beanFactory.initBeanWrapper(bw);

        Constructor constructorToUse = null;
        ArgumentsHolder argsHolderToUse = null;
        Object[] argsToUse = null;

        /**
         * 明确传入了参数，则就使用它
         * 否则看缓存中是否已经有了解析过的构造器参数
         */
        if (explicitArgs != null) {
            argsToUse = explicitArgs;
        }
        else {
            Object[] argsToResolve = null;
            synchronized (mbd.constructorArgumentLock) {
                constructorToUse = (Constructor) mbd.resolvedConstructorOrFactoryMethod;
                if (constructorToUse != null && mbd.constructorArgumentsResolved) {
                    // Found a cached constructor...
                    argsToUse = mbd.resolvedConstructorArguments;
                    if (argsToUse == null) {
                        argsToResolve = mbd.preparedConstructorArguments;
                    }
                }
            }
            /**
             * 只能解析了
             */
            if (argsToResolve != null) {
                argsToUse = resolvePreparedArguments(beanName, mbd, bw, constructorToUse, argsToResolve);
            }
        }

        if (constructorToUse == null) {
            // Need to resolve the constructor.
            boolean autowiring = (chosenCtors != null ||
                    mbd.getResolvedAutowireMode() == RootBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            ConstructorArgumentValues resolvedValues = null;

            int minNrOfArgs;
            if (explicitArgs != null) {
                minNrOfArgs = explicitArgs.length;
            }
            else {
                ConstructorArgumentValues cargs = mbd.getConstructorArgumentValues();
                resolvedValues = new ConstructorArgumentValues();
                minNrOfArgs = resolveConstructorArguments(beanName, mbd, bw, cargs, resolvedValues);
            }

            // Take specified constructors, if any.
            Constructor[] candidates = chosenCtors;
            if (candidates == null) {
                Class beanClass = mbd.getBeanClass();
                try {
                    candidates = (mbd.isNonPublicAccessAllowed() ?
                            beanClass.getDeclaredConstructors() : beanClass.getConstructors());
                }
                catch (Throwable ex) {
                    throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                            "Resolution of declared constructors on bean Class [" + beanClass.getName() +
                                    "] from ClassLoader [" + beanClass.getClassLoader() + "] failed", ex);
                }
            }
            AutowireUtils.sortConstructors(candidates);
            int minTypeDiffWeight = Integer.MAX_VALUE;
            Set<Constructor> ambiguousConstructors = null;
            List<Exception> causes = null;

            for (int i = 0; i < candidates.length; i++) {
                Constructor<?> candidate = candidates[i];
                Class[] paramTypes = candidate.getParameterTypes();

                if (constructorToUse != null && argsToUse.length > paramTypes.length) {
                    // Already found greedy constructor that can be satisfied ->
                    // do not look any further, there are only less greedy constructors left.
                    break;
                }
                if (paramTypes.length < minNrOfArgs) {
                    continue;
                }

                ArgumentsHolder argsHolder;
                if (resolvedValues != null) {
                    try {
                        String[] paramNames = null;
                        if (constructorPropertiesAnnotationAvailable) {
                            paramNames = ConstructorPropertiesChecker.evaluateAnnotation(candidate, paramTypes.length);
                        }
                        if (paramNames == null) {
                            ParameterNameDiscoverer pnd = this.beanFactory.getParameterNameDiscoverer();
                            if (pnd != null) {
                                paramNames = pnd.getParameterNames(candidate);
                            }
                        }
                        argsHolder = createArgumentArray(
                                beanName, mbd, resolvedValues, bw, paramTypes, paramNames, candidate, autowiring);
                    }
                    catch (UnsatisfiedDependencyException ex) {
                        if (this.beanFactory.logger.isTraceEnabled()) {
                            this.beanFactory.logger.trace(
                                    "Ignoring constructor [" + candidate + "] of bean '" + beanName + "': " + ex);
                        }
                        if (i == candidates.length - 1 && constructorToUse == null) {
                            if (causes != null) {
                                for (Exception cause : causes) {
                                    this.beanFactory.onSuppressedException(cause);
                                }
                            }
                            throw ex;
                        }
                        else {
                            // Swallow and try next constructor.
                            if (causes == null) {
                                causes = new LinkedList<Exception>();
                            }
                            causes.add(ex);
                            continue;
                        }
                    }
                }
                else {
                    // Explicit arguments given -> arguments length must match exactly.
                    if (paramTypes.length != explicitArgs.length) {
                        continue;
                    }
                    argsHolder = new ArgumentsHolder(explicitArgs);
                }

                int typeDiffWeight = (mbd.isLenientConstructorResolution() ?
                        argsHolder.getTypeDifferenceWeight(paramTypes) : argsHolder.getAssignabilityWeight(paramTypes));
                // Choose this constructor if it represents the closest match.
                if (typeDiffWeight < minTypeDiffWeight) {
                    constructorToUse = candidate;
                    argsHolderToUse = argsHolder;
                    argsToUse = argsHolder.arguments;
                    minTypeDiffWeight = typeDiffWeight;
                    ambiguousConstructors = null;
                }
                else if (constructorToUse != null && typeDiffWeight == minTypeDiffWeight) {
                    if (ambiguousConstructors == null) {
                        ambiguousConstructors = new LinkedHashSet<Constructor>();
                        ambiguousConstructors.add(constructorToUse);
                    }
                    ambiguousConstructors.add(candidate);
                }
            }

            if (constructorToUse == null) {
                throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                        "Could not resolve matching constructor " +
                                "(hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)");
            }
            else if (ambiguousConstructors != null && !mbd.isLenientConstructorResolution()) {
                throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                        "Ambiguous constructor matches found in bean '" + beanName + "' " +
                                "(hint: specify index/type/name arguments for simple parameters to avoid type ambiguities): " +
                                ambiguousConstructors);
            }

            if (explicitArgs == null) {
                argsHolderToUse.storeCache(mbd, constructorToUse);
            }
        } // end constructorToUse == null

        try {
            Object beanInstance;

            if (System.getSecurityManager() != null) {
                final Constructor ctorToUse = constructorToUse;
                final Object[] argumentsToUse = argsToUse;
                beanInstance = AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    public Object run() {
                        return beanFactory.getInstantiationStrategy().instantiate(
                                mbd, beanName, beanFactory, ctorToUse, argumentsToUse);
                    }
                }, beanFactory.getAccessControlContext());
            }
            else {
                beanInstance = this.beanFactory.getInstantiationStrategy().instantiate(
                        mbd, beanName, this.beanFactory, constructorToUse, argsToUse);
            }

            bw.setWrappedInstance(beanInstance);
            return bw;
        }
        catch (Throwable ex) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Instantiation of bean failed", ex);
        }
    }

    /**
     * 具体实例化策略的实例化方法， 这里是 SimpleInstantiationStrategy
     */
    public Object instantiate(RootBeanDefinition beanDefinition, String beanName, BeanFactory owner,
                              final Constructor<?> ctor, Object[] args) {

        /**
         * 没有需要动态代理的方法直接反射即可
         */
        if (beanDefinition.getMethodOverrides().isEmpty()) {
            if (System.getSecurityManager() != null) {
                // use own privileged to change accessibility (when security is on)
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    public Object run() {
                        ReflectionUtils.makeAccessible(ctor);
                        return null;
                    }
                });
            }
            return BeanUtils.instantiateClass(ctor, args);
        }
        else {
            /**
             * 需要CGLIB
             */
            return instantiateWithMethodInjection(beanDefinition, beanName, owner, ctor, args);
        }
    }

    /**
     * 见 CglibSubclassingInstantiationStrategy
     */
    @Override
    protected Object instantiateWithMethodInjection(
            RootBeanDefinition beanDefinition, String beanName, BeanFactory owner) {

        // Must generate CGLIB subclass.
        return new CglibSubclassCreator(beanDefinition, owner).instantiate(null, null);
    }

    /**
     * Create a new instance of a dynamically generated subclasses implementing the
     * required lookups.
     * @param ctor constructor to use. If this is <code>null</code>, use the
     * no-arg constructor (no parameterization, or Setter Injection)
     * @param args arguments to use for the constructor.
     * Ignored if the ctor parameter is <code>null</code>.
     * @return new instance of the dynamically generated class
     */
    public Object instantiate(Constructor ctor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.beanDefinition.getBeanClass());
        enhancer.setCallbackFilter(new CallbackFilterImpl());
        enhancer.setCallbacks(new Callback[] {
                NoOp.INSTANCE,
                new LookupOverrideMethodInterceptor(),
                new ReplaceOverrideMethodInterceptor()
        });

        return (ctor == null) ?
                enhancer.create() :
                enhancer.create(ctor.getParameterTypes(), args);
    }

}