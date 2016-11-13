package com.vonzhou.learning.note;

class Main {

    // -------------------- HttpServlet ----------------------------------
    /**
     * @version 2016/11/13.
     * <p>
     * <p>
     * Dispatches client requests to the protected
     * <code>service</code> method. There's no need to
     * override this method.
     * @param req    the {@link HttpServletRequest} object that
     * contains the request the client made of
     * the servlet
     * @param res    the {@link HttpServletResponse} object that
     * contains the response the servlet returns
     * to the client
     * @exception IOException    if an input or output error occurs
     * while the servlet is handling the
     * HTTP request
     * @exception ServletException    if the HTTP request cannot
     * be handled
     * @param req    the {@link HttpServletRequest} object that
     * contains the request the client made of
     * the servlet
     * @param res    the {@link HttpServletResponse} object that
     * contains the response the servlet returns
     * to the client
     * @exception IOException    if an input or output error occurs
     * while the servlet is handling the
     * HTTP request
     * @exception ServletException    if the HTTP request cannot
     * be handled
     * @see javax.servlet.Servlet#service
     * <p>
     * <p>
     * Dispatches client requests to the protected
     * <code>service</code> method. There's no need to
     * override this method.
     * @see javax.servlet.Servlet#service
     */

    /**
     * Dispatches client requests to the protected
     * <code>service</code> method. There's no need to
     * override this method.
     *
     * @param req the {@link HttpServletRequest} object that
     *            contains the request the client made of
     *            the servlet
     * @param res the {@link HttpServletResponse} object that
     *            contains the response the servlet returns
     *            to the client
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          HTTP request
     * @throws ServletException if the HTTP request cannot
     *                          be handled
     * @see javax.servlet.Servlet#service
     */

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) res;
        } catch (ClassCastException e) {
            throw new ServletException("non-HTTP request or response");
        }
        service(request, response);
    }

    /**
     * Receives standard HTTP requests from the public
     * <code>service</code> method and dispatches
     * them to the <code>do</code><i>XXX</i> methods defined in
     * this class. This method is an HTTP-specific version of the
     * {@link javax.servlet.Servlet#service} method. There's no
     * need to override this method.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          HTTP request
     * @throws ServletException if the HTTP request
     *                          cannot be handled
     * @see javax.servlet.Servlet#service
     */

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String method = req.getMethod();

        if (method.equals(METHOD_GET)) {
            long lastModified = getLastModified(req);
            if (lastModified == -1) {
                // servlet doesn't support if-modified-since, no reason
                // to go through further expensive logic
                doGet(req, resp);
            } else {
                long ifModifiedSince = req.getDateHeader(HEADER_IFMODSINCE);
                if (ifModifiedSince < (lastModified / 1000 * 1000)) {
                    // If the servlet mod time is later, call doGet()
                    // Round down to the nearest second for a proper compare
                    // A ifModifiedSince of -1 will always be less
                    maybeSetLastModified(resp, lastModified);
                    doGet(req, resp);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                }
            }

        } else if (method.equals(METHOD_HEAD)) {
            long lastModified = getLastModified(req);
            maybeSetLastModified(resp, lastModified);
            doHead(req, resp);

        } else if (method.equals(METHOD_POST)) {
            doPost(req, resp);

        } else if (method.equals(METHOD_PUT)) {
            doPut(req, resp);

        } else if (method.equals(METHOD_DELETE)) {
            doDelete(req, resp);

        } else if (method.equals(METHOD_OPTIONS)) {
            doOptions(req, resp);

        } else if (method.equals(METHOD_TRACE)) {
            doTrace(req, resp);

        } else {
            //
            // Note that this means NO servlet supports whatever
            // method was requested, anywhere on this server.
            //

            String errMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[1];
            errArgs[0] = method;
            errMsg = MessageFormat.format(errMsg, errArgs);

            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
        }
    }

    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     * <p>
     * <p>Overriding this method to support a GET request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a GET request that returns no body in the
     * response, only the request header fields.
     * <p>
     * <p>When overriding this method, read the request data,
     * write the response headers, get the response's writer or
     * output stream object, and finally, write the response data.
     * It's best to include content type and encoding. When using
     * a <code>PrintWriter</code> object to return the response,
     * set the content type before accessing the
     * <code>PrintWriter</code> object.
     * <p>
     * <p>The servlet container must write the headers before
     * committing the response, because in HTTP the headers must be sent
     * before the response body.
     * <p>
     * <p>Where possible, set the Content-Length header (with the
     * {@link javax.servlet.ServletResponse#setContentLength} method),
     * to allow the servlet container to use a persistent connection
     * to return its response to the client, improving performance.
     * The content length is automatically set if the entire response fits
     * inside the response buffer.
     * <p>
     * <p>When using HTTP 1.1 chunked encoding (which means that the response
     * has a Transfer-Encoding header), do not set the Content-Length header.
     * <p>
     * <p>The GET method should be safe, that is, without
     * any side effects for which users are held responsible.
     * For example, most form queries have no side effects.
     * If a client request is intended to change stored data,
     * the request should use some other HTTP method.
     * <p>
     * <p>The GET method should also be idempotent, meaning
     * that it can be safely repeated. Sometimes making a
     * method safe also makes it idempotent. For example,
     * repeating queries is both safe and idempotent, but
     * buying a product online or modifying data is neither
     * safe nor idempotent.
     * <p>
     * <p>If the request is incorrectly formatted, <code>doGet</code>
     * returns an HTTP "Bad Request" message.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the GET request
     * @throws ServletException if the request for the GET
     *                          could not be handled
     * @see javax.servlet.ServletResponse#setContentType
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
        }
    }

    //------------------------- FrameworkServlet -------------------

    /**
     * Delegate GET requests to processRequest/doService.
     * <p>Will also be invoked by HttpServlet's default implementation of <code>doHead</code>,
     * with a <code>NoBodyResponse</code> that just captures the content length.
     *
     * @see #doService
     * @see #doHead
     */
    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Process this request, publishing an event regardless of the outcome.
     * <p>The actual event handling is performed by the abstract
     * {@link #doService} template method.
     */
    protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        Throwable failureCause = null;

        // Expose current LocaleResolver and request as LocaleContext.
        LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
        LocaleContextHolder.setLocaleContext(buildLocaleContext(request), this.threadContextInheritable);

        // Expose current RequestAttributes to current thread.
        RequestAttributes previousRequestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = null;
        if (previousRequestAttributes == null || previousRequestAttributes.getClass().equals(ServletRequestAttributes.class)) {
            requestAttributes = new ServletRequestAttributes(request);
            RequestContextHolder.setRequestAttributes(requestAttributes, this.threadContextInheritable);
        }

        if (logger.isTraceEnabled()) {
            logger.trace("Bound request context to thread: " + request);
        }

        try {
            doService(request, response);
        } catch (ServletException ex) {
            failureCause = ex;
            throw ex;
        } catch (IOException ex) {
            failureCause = ex;
            throw ex;
        } catch (Throwable ex) {
            failureCause = ex;
            throw new NestedServletException("Request processing failed", ex);
        } finally {
            // Clear request attributes and reset thread-bound context.
            LocaleContextHolder.setLocaleContext(previousLocaleContext, this.threadContextInheritable);
            if (requestAttributes != null) {
                RequestContextHolder.setRequestAttributes(previousRequestAttributes, this.threadContextInheritable);
                requestAttributes.requestCompleted();
            }
            if (logger.isTraceEnabled()) {
                logger.trace("Cleared thread-bound request context: " + request);
            }

            if (failureCause != null) {
                this.logger.debug("Could not complete request", failureCause);
            } else {
                this.logger.debug("Successfully completed request");
            }
            if (this.publishEvents) {
                // Whether or not we succeeded, publish an event.
                long processingTime = System.currentTimeMillis() - startTime;
                this.webApplicationContext.publishEvent(
                        new ServletRequestHandledEvent(this,
                                request.getRequestURI(), request.getRemoteAddr(),
                                request.getMethod(), getServletConfig().getServletName(),
                                WebUtils.getSessionId(request), getUsernameForRequest(request),
                                processingTime, failureCause));
            }
        }
    }

    /**
     * Subclasses must implement this method to do the work of request handling,
     * receiving a centralized callback for GET, POST, PUT and DELETE.
     * <p>The contract is essentially the same as that for the commonly overridden
     * <code>doGet</code> or <code>doPost</code> methods of HttpServlet.
     * <p>This class intercepts calls to ensure that exception handling and
     * event publication takes place.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @throws Exception in case of any kind of processing failure
     * @see javax.servlet.http.HttpServlet#doGet
     * @see javax.servlet.http.HttpServlet#doPost
     */
    protected abstract void doService(HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    //------------------------- DispatcherServlet ------------------------

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            String requestUri = urlPathHelper.getRequestUri(request);
            logger.debug("DispatcherServlet with name '" + getServletName() + "' processing " + request.getMethod() +
                    " request for [" + requestUri + "]");
        }

        /**
         * Make framework objects available to handlers and view objects.
         * 为了处理器对象，视图对象可以访问某些某些全局的对象，设置到HTTP request的属性中
         * */
        request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, getWebApplicationContext());
        request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
        request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
        request.setAttribute(THEME_SOURCE_ATTRIBUTE, getThemeSource());

        try {
            doDispatch(request, response);
        } finally {
        }
    }

    /**
     * 具体分发请求到handler
     * <p>The handler will be obtained by applying the servlet's HandlerMappings in order.
     * The HandlerAdapter will be obtained by querying the servlet's installed HandlerAdapters
     * to find the first that supports the handler class.
     * <p>All HTTP methods are handled by this method. It's up to HandlerAdapters or handlers
     * themselves to decide which methods are acceptable.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @throws Exception in case of any kind of processing failure
     */
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        int interceptorIndex = -1;

        try {
            ModelAndView mv;
            boolean errorView = false;

            try {
                processedRequest = checkMultipart(request);

                /**
                 * 获取该请求对应的 handler和interceptor（定位到了某个类）
                 * Determine handler for the current request.
                 * */
                mappedHandler = getHandler(processedRequest, false);
                if (mappedHandler == null || mappedHandler.getHandler() == null) {
                    noHandlerFound(processedRequest, response);
                    return;
                }

                /**
                 * 获得该请求对应的更具体的handler（适配器模式，包含更多的策略和配置），如AnnotationMethodHandlerAdapter
                 * Determine handler adapter for the current request.
                 * */
                HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

                /**
                 * 依次执行我们的Interceptor
                 * Apply preHandle methods of registered interceptors.
                 * */
                HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
                if (interceptors != null) {
                    for (int i = 0; i < interceptors.length; i++) {
                        HandlerInterceptor interceptor = interceptors[i];
                        if (!interceptor.preHandle(processedRequest, response, mappedHandler.getHandler())) {
                            /**
                             * 如果该preHandle方法返回了false，那么就倒序依次执行前面的Interceptor的 afterCompletion 方法，然后返回
                             * 所以不会执行后面的 Controller的handler方法，以及Interceptor的postHandle方法
                             * */
                            triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, null);
                            return;
                        }
                        interceptorIndex = i;
                    }
                }

                /**
                 * 真正进入 Controller handler 进行请求处理
                 * Actually invoke the handler.
                 * */
                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

                // Do we need view name translation?
                if (mv != null && !mv.hasView()) {
                    mv.setViewName(getDefaultViewName(request));
                }

                //
                /**
                 * 然后逆序依次执行我们的Interceptor的 postHandle 方法
                 * Apply postHandle methods of registered interceptors.
                 * */
                if (interceptors != null) {
                    for (int i = interceptors.length - 1; i >= 0; i--) {
                        HandlerInterceptor interceptor = interceptors[i];
                        interceptor.postHandle(processedRequest, response, mappedHandler.getHandler(), mv);
                    }
                }
            } catch (ModelAndViewDefiningException ex) {
                logger.debug("ModelAndViewDefiningException encountered", ex);
                mv = ex.getModelAndView();
            } catch (Exception ex) {
                Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
                mv = processHandlerException(processedRequest, response, handler, ex);
                errorView = (mv != null);
            }

            // Did the handler return a view to render?
            if (mv != null && !mv.wasCleared()) {
                render(mv, processedRequest, response);
                if (errorView) {
                    WebUtils.clearErrorRequestAttributes(request);
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Null ModelAndView returned to DispatcherServlet with name '" + getServletName() +
                            "': assuming HandlerAdapter completed request handling");
                }
            }

            /** 此时所有的Interceptor都顺利完成了（preHandle -> handler -> postHandle），
             * 此时倒序依次执行所有的Interceptor的 afterCompletion方法
             * Trigger after-completion for successful outcome.
             * */
            triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, null);
        } catch (Exception ex) {
            // Trigger after-completion for thrown exception.
            triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, ex);
            throw ex;
        } catch (Error err) {
            ServletException ex = new NestedServletException("Handler processing failed", err);
            // Trigger after-completion for thrown exception.
            triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, ex);
            throw ex;
        } finally {
            // Clean up any resources used by a multipart request.
            if (processedRequest != request) {
                cleanupMultipart(processedRequest);
            }
        }
    }


    private void triggerAfterCompletion(HandlerExecutionChain mappedHandler,
                                        int interceptorIndex,
                                        HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception ex) throws Exception {

        // Apply afterCompletion methods of registered interceptors.
        if (mappedHandler != null) {
            HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
            if (interceptors != null) {
                for (int i = interceptorIndex; i >= 0; i--) {
                    HandlerInterceptor interceptor = interceptors[i];
                    try {
                        interceptor.afterCompletion(request, response, mappedHandler.getHandler(), ex);
                    } catch (Throwable ex2) {
                        logger.error("HandlerInterceptor.afterCompletion threw exception", ex2);
                    }
                }
            }
        }
    }


}