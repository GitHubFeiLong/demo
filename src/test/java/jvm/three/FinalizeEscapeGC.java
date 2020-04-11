package jvm.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

/**
 * 此代码演示了两点：
 * 1.对象可以在被GC时自我拯救。
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 */

public class FinalizeEscapeGC {

    private static final Logger log = LoggerFactory.getLogger(FinalizeEscapeGC.class);

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        log.info("yes, i'm still alive :)");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("finalize method executed!");

        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String ... args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            log.info("no, i'm dead :(");
        }

        // 以下这段代码与上面地完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            log.info("no, i'm dead :(");
        }

    }
}
