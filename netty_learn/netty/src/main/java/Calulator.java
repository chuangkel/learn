import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-28 15:58
 **/
public enum  Calulator {

    Instance;
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }
}
