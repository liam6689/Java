package com.nuist.designpattern.principal.icp;


/**
 * @author 陈志远
 * @version 1.0
 * @create 2023-06-23 22:54
 * @description
 */
public class OCP {
    public static void main(String[] args) {
//    使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawCircle(new Circle());

        graphicEditor.drawTriangle(new Triangle());
    }
}

//这是一个用于绘图的类【使用方】
class GraphicEditor{
//    接收Shape对象，然后根据type，来绘制不同的图形
    public void drawShape(Shape s){
        if (s.m_type == 1){
            drawRectangle(s);
        }else if(s.m_type == 2){
            drawCircle(s);
        }else if(s.m_type == 3){
            drawTriangle(s);
        }
    }
//    绘制矩形
    public void drawRectangle(Shape r){
        System.out.println("绘制矩形");
    }
//    绘制圆形
    public void drawCircle(Shape r){
        System.out.println("绘制圆形");
    }

//    绘制三角形
    public void drawTriangle(Shape r){
        System.out.println("绘制三角形");
    }
}
//Shape类，基类
class Shape{
    int m_type;
}

class Rectangle extends Shape{
    Rectangle(){
        super.m_type = 1;//在构建的时候给它赋不同的类型
    }
}

class Circle extends Shape{
    Circle(){
        super.m_type = 2;
    }
}

//新增画三角形【这个没问题】
class Triangle extends Shape{
    Triangle(){
        super.m_type = 2;
    }
}