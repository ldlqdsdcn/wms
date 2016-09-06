package com.delmar.devs;

import com.delmar.devs.swing.InputFrame;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by admin on 2016/9/6.
 * generate code main class
 */
public final class GenerationMain {
    private static final Logger logger= Logger.getLogger(GenerationMain.class);
    public static void main(String[] args)
    {
        logger.debug("begin generate code:"+Locale.getDefault());
        ResourceBundle resourceBundle=  ResourceBundle.getBundle("msg",Locale.getDefault());
        InputFrame.main(resourceBundle);
    }
}
