package com.vonzhou.learn.callpython;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * @version 2017/2/6.
 */
public class CallPython2 {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\test.py");
        PyFunction func = (PyFunction)interpreter.get("add",PyFunction.class);
        PyObject pyobj = func.__call__(new PyInteger(2016),new PyInteger(2016));
        System.out.println("retMsg = " + pyobj);
    }
}
