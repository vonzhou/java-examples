/**
 * lookup-method, replace-method 的配置
 */
public class Main{
    /**
     * Validate and prepare the given method override.
     * Checks for existence of a method with the specified name,
     * marking it as not overloaded if none found.
     * @param mo the MethodOverride object to validate
     * @throws BeanDefinitionValidationException in case of validation failure
     */
    protected void prepareMethodOverride(MethodOverride mo) throws BeanDefinitionValidationException {
        int count = ClassUtils.getMethodCountForName(getBeanClass(), mo.getMethodName());
        /** 根本没有这个方法，覆盖个毛线 **/
        if (count == 0) {
            throw new BeanDefinitionValidationException(
                    "Invalid method override: no method with name '" + mo.getMethodName() +
                            "' on class [" + getBeanClassName() + "]");
        }
        else if (count == 1) {
            /**
             * 只有自己拥有这个方法，所以不是 override
             *  Mark override as not overloaded, to avoid the overhead of arg type checking.
              */
            mo.setOverloaded(false);
        }
    }


    /**
     * 查找和这个Class相关的类中有多少个名为methodName的方法
     * 包括：该类本身， 该类实现的接口， 该类的父类， 递归...
     * Return the number of methods with a given name (with any argument types),
     * for the given class and/or its superclasses. Includes non-public methods.
     * @param clazz	the clazz to check
     * @param methodName the name of the method
     * @return the number of methods with the given name
     */
    public static int getMethodCountForName(Class<?> clazz, String methodName) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull(methodName, "Method name must not be null");
        int count = 0;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (methodName.equals(method.getName())) {
                count++;
            }
        }
        Class<?>[] ifcs = clazz.getInterfaces();
        for (Class<?> ifc : ifcs) {
            count += getMethodCountForName(ifc, methodName);
        }
        if (clazz.getSuperclass() != null) {
            count += getMethodCountForName(clazz.getSuperclass(), methodName);
        }
        return count;
    }

}