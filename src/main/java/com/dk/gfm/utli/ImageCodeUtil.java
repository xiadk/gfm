package com.dk.gfm.utli;

import com.dk.gfm.common.ResultInfo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ImageCodeUtil
 * @Description 图片验证码工具类
 * @Author xiadekang
 * @Date 2018/9/10 10:58
 * @Version 1.0
 **/
public class ImageCodeUtil {
    public final static String LOGIN_CODE="login_code";


    public static  ResultInfo getImageCode(){
        ResultInfo resultInfo = new ResultInfo();

        // 在内存中创建图象
        int width = 65, height = 20;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics graphics = bufferedImage.getGraphics();
        // 设定背景色
        graphics.setColor(ImageCodeUtil.getColor(230, 255));
        graphics.fillRect(0, 0, 100, 25);
        // 设定字体
        graphics.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18));
        // 产生0条干扰线，
        graphics.drawLine(0, 0, 0, 0);
        // 生成随机类
        Random random = new Random();
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            graphics.setColor(ImageCodeUtil.getColor(100, 150));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            graphics.drawString(rand, 15 * i + 6, 16);
        }
        for (int i = 0; i < (random.nextInt(5) + 5); i++) {
            graphics.setColor(new Color(random.nextInt(255) + 1, random.nextInt(255) + 1, random.nextInt(255) + 1));
            graphics.drawLine(random.nextInt(100), random.nextInt(30), random.nextInt(100), random.nextInt(30));
        }
        // 图象生效
        graphics.dispose();

        List<Object> list = new ArrayList<>();
        list.add(sRand);
        list.add(bufferedImage);
        resultInfo.code = ResultInfo.SUCCESS;
        resultInfo.msg = "生成图形验证码成功";
        resultInfo.obj=list;

        return resultInfo;
    }

    public static Color getColor(int fc, int bc){

        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
