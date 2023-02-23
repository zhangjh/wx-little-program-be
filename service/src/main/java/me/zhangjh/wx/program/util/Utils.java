package me.zhangjh.wx.program.util;

/**
 * @author zhangjh451@midea.com
 * @date 11:37 AM 2023/2/22
 * @Description
 */
public class Utils {

    public static boolean isCharacter(Character character) {
        if(character == null || character == ' ') {
            return false;
        }
        return character.toString().matches("[\\u4e00-\\u9fa5]");
    }
}
