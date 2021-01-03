/*
 * Copyright 2021 Allen-Lin <allen.killer3724@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This project is inspired by my teacher Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * And I want you to know that everyone has the same feeling.
 * We suffering from depression,blue,sorrow,feeling painful and disappointed,getting anger.
 * It's ok. You are not alone. You are a good person.
 */
package art.cctcc.c1635.antsomg.demo;

import java.awt.Color;
import java.util.Map;
import static java.util.function.Predicate.not;
import java.util.stream.Collectors;
import processing.core.PApplet;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class Main extends PApplet {

    int size1;
    int size2;
    HarassmentSystem emotion_1;
    HarassmentSystem emotion_2;
    HarassmentSystem emotion_3;
    float theta;
    Map<HarassmentCurve, Float>  math;//斜率
    Map<HarassmentCurve, Float> radius;
    Map<HarassmentCurve, Float> radius2;
    Map<HarassmentCurve, Float>  kool;
    Map<HarassmentCurve, Float>  amaze;

    @Override
    public void settings() {
        size(size2, size1);
        fullScreen();
    }

    @Override
    public void setup() {
        colorMode(RGB);
        frameRate(30);
        background(30);
        noFill();
        emotion_1 = new HarassmentSystem(150);
        emotion_1.init_graphs();
        emotion_1.init_population();
        emotion_2 = new HarassmentSystem(150);
        emotion_2.init_graphs();
        emotion_2.init_population();
        emotion_3 = new HarassmentSystem(200);
        emotion_3.init_graphs();
        emotion_3.init_population();
        
        math = emotion_1.ants.stream()
                .collect(Collectors.toMap(ant -> ant, ant -> 20f));
        radius = emotion_1.ants.stream()
                .collect(Collectors.toMap(ant -> ant, ant -> 10f));
        radius2 = emotion_1.ants.stream()
                .collect(Collectors.toMap(ant -> ant, ant -> 7f));
        kool = emotion_2.ants.stream()
                .collect(Collectors.toMap(ant -> ant, ant -> 8f));
        amaze = emotion_3.ants.stream()
                .collect(Collectors.toMap(ant -> ant, ant -> 9f));
    }
    float move_amount = 3.5f;
    float delta_theta = 1f;//負號反向

    @Override
    public void draw() {

        emotion_1.ants.stream()
                .filter(not(HarassmentCurve::isCompleted))
                .forEach(ant -> {
                    var move = ant.getCurrentTrace().getDimension("x");
                    var r = radius.get(ant);
                    var m = math.get(ant);
                    var r2 =radius2.get(ant);
                    
                    if ("LINE".equals(move.getName()) && r > move_amount) {
                        
                        r -= move_amount;
                        m -= move_amount/3;
                        r2 -= move_amount/2;
                        
                    }
                    if ("SPIRAL".equals(move.getName())) {
                        m += 2*move_amount;
                    }
                    if ("TEST".equals(move.getName())) {
                        r += move_amount/3;
                        m -= 1/7*move_amount;
                        r2 += move_amount/2;
                    }
                    radius.replace(ant, r);
                    radius2.replace(ant,r2);
                    float   x = (float) ( size1 / 2 + Math.pow(m,3)/23 - 2*r * cos(this.theta-random(-50, 40))*cos(this.theta)+ r2 *3* cos(this.theta)/2 +random(-10, 10)/PI-200),
                            y = (float) (random(20, 500) + size2 / 2 + Math.pow((x-200)/3,random(0,4))/83 + r * sin(this.theta + random(-40, 10))-r2*cos(this.theta)-200);
                            
                    switch (ant.currentTrace.getDimension("y").getName()) {
                        case "WHITE" -> {
                            stroke(Color.WHITE.getRGB(),100);
                        }
                        case "RED" -> {
                            stroke(Color.RED.getRGB(),50);
                        }
                        case "YELLOW" -> {
                            stroke(Color.YELLOW.getRGB(),50);
                        }
                        case "BLUE" -> {
                            stroke(Color.BLUE.getRGB(),60);
                        }
                        case "GREEN" -> {
                            stroke(Color.GREEN.getRGB(),50);
                        }
                        case "PURPLE" -> {
                            stroke(153,51,250,50);
                        }
                        case "BLACK" -> {
                            stroke(Color.BLACK.getRGB(),180);
                        }
                        case "LAKE" -> {
                            stroke(127, 255, 212,100);
                        }
                        case "SUN" -> {
                            stroke(255, 97, 0,70);
                        }
                        case "PINK" -> {
                            stroke(255, 192, 203,70);
                        }
                        
                    }
                    float x1=r*sin(theta);
                    float x2=r2*tan(theta+random(-10,10));
                    
                    noFill();
                    strokeWeight(2);
                    beginShape();
                    curveVertex((x-random(0,50)-100)/4,(y-15)/2);
                    curveVertex((x*3-random(40,100))/2,y/3);
                    curveVertex((x-y)/7,(x-2*y)/9);
                    curveVertex(5*m/12,r*r2/600);
                    curveVertex((x-2*y)/2,5*m/13);
                    curveVertex(m/2,r/7);
                    curveVertex(3*x/5,101*y/199);
                    curveVertex(x-x1,x2/2);
                    curveVertex(size1/2,size2/2);
                    endShape();
                    
                    strokeWeight(2);
                    beginShape();
                    curveVertex(size1-r-random(-3,5),size2-r2*sin(theta));
                    curveVertex((random(100,400)-x/12)/2,y/3-r2/46);
                    curveVertex(size1/4-min(r/44,r2/27,abs(random(0,345)-r)),size2/3+random(-15,10)+ceil(r2));
                    curveVertex(800-5*m/12,r-r*r2/600);
                    curveVertex(150-min(size1/3,r/7,x1-x),150+random(-5,20));
                    endShape();
                    
                    noFill();
                    strokeWeight(1);
                    beginShape();
                    curveVertex(x1/2-m/1000,y/2+random(0,7));
                    curveVertex(x*sin(this.theta)/6,y/20+random(0,40));
                    curveVertex(floor(x/103),y/88);
                    curveVertex((x1+x2)/55-r2/107,ceil(y/8-r-random(0,75)));
                    curveVertex(2*size1/3,3*size2/4);
                    endShape();
                    
                    strokeWeight(1);
                    beginShape();
                    curveVertex(size1-random(0,300),size2*7/9-r/20);
                    curveVertex(size1/2-r,m-pow(r2,2)/14);
                    curveVertex(ceil((size1-random(0,70))/3)-r2+r,m/4);
                    curveVertex(x1,x);
                    curveVertex(y-r-r2,x+y-2*r);
                    endShape();
                    
                    //鎖定點出現的位置(確保畫面的平衡):使用常數(size或另外設置)、floor跟ceil函數
                    //增加震盪感:sin函數 cos函數
                    //調整紊亂感:加入random亂數，讓圖形更能跑到塗上每一個位置
                });
        emotion_2.ants.stream()
                .filter(not(HarassmentCurve::isCompleted))
                .forEach(ant -> {
                    var oh = ant.getCurrentTrace().getDimension("x");
                    var k = kool.get(ant);
                    if ("LINE".equals(oh.getName())){
                       k -= 2*move_amount;
                    }
                    if ("TEST".equals(oh.getName())){
                       k += 2*move_amount;
                    }
                    kool.replace(ant, k);
                    float z = (float)(size1/5+k*cos(theta)+k-random(-5,5));
                    float w = (float)(size2/3+k*cos(theta)*sin(theta));
                    switch (ant.currentTrace.getDimension("y").getName()) {
                        case "WHITE" -> {
                            stroke(Color.WHITE.getRGB(),50);
                        }
                        case "RED" -> {
                            stroke(Color.RED.getRGB(),50);
                        }
                        case "YELLOW" -> {
                            stroke(Color.YELLOW.getRGB(),50);
                        }
                        case "BLUE" -> {
                            stroke(Color.BLUE.getRGB(),45);
                        }
                        case "GREEN" -> {
                            stroke(Color.GREEN.getRGB(),50);
                        }
                        case "PURPLE" -> {
                            stroke(153,51,250,100);
                        }
                        case "BLACK" -> {
                            stroke(Color.BLACK.getRGB(),165);
                        }
                        case "LAKE" -> {
                            stroke(127, 255, 212,50);
                        }
                        case "SUN" -> {
                            stroke(255, 97, 0,50);
                        }
                        case "PINK" -> {
                            stroke(255, 192, 203,50);
                        }
                    }
                    float x3=k/(this.theta)*cos(this.theta);
                    float x4=k/(this.theta)*sin(this.theta);
                    float x5=pow(k,2)*tan(theta+random(-10,10));
                    
                    noFill();
                    strokeWeight(1);
                    beginShape();
                    curveVertex(size1*5/6-random(-70,70), size2*8/9-random(-50,50));
                    curveVertex(size1*7/9-x3,size2*7/9-x4);
                    curveVertex(size1*7/9-x5/3,size2*7/18-ceil(x5/3-random(-50,50)));
                    curveVertex(size1*5/9+x3*x4,size2*5/9+x3*x4*x5);
                    curveVertex(ceil((size1-z)), 7*w/4);
                    curveVertex(k*(k+1),x5/theta);
                    endShape();
                });
        emotion_3.ants.stream()
                .filter(not(HarassmentCurve::isCompleted))
                .forEach(ant -> {
                    var please = ant.getCurrentTrace().getDimension("x");
                    var a = amaze.get(ant);
                    if ("LINE".equals(please.getName())){
                       a -= 3*move_amount/2;
                    }
                    if ("TEST".equals(please.getName())){
                       a += 3*move_amount/2;
                    }
                    amaze.replace(ant, a);
                    float s = size1/2+random(-5,5)-a*sin(this.theta);
                    float t = size2/2-a*cos(this.theta);
                    switch (ant.currentTrace.getDimension("y").getName()) {
                        case "WHITE" -> {
                            stroke(255, 250, 250,70);
                        }
                        case "RED" -> {
                            stroke(255, 127, 80,70);
                        }
                        case "YELLOW" -> {
                            stroke(255, 215, 0,70);
                        }
                        case "BLUE" -> {
                            stroke(0, 255, 255,65);
                        }
                        case "GREEN" -> {
                            stroke(189, 252, 201,70);
                        }
                        case "PURPLE" -> {
                            stroke(211, 160, 211,70);
                        }
                        case "BLACK" -> {
                            stroke(41, 36, 33,130);
                        }
                        case "LAKE" -> {
                            stroke(127, 255, 212,70);
                        }
                        case "SUN" -> {
                            stroke(255, 97, 0,70);
                        }
                        case "PINK" -> {
                            stroke(255, 192, 203,70);
                        }
                    }
                    
                    noFill();
                    strokeWeight(1);
                    beginShape();
                    curveVertex(size1/3*sin(PI/3+random(-2,2)),size2/3*cos(PI/3+random(-2,2)));
                    curveVertex(size1/3+s*sin(PI/3+random(-2,2)),size2/3+t*cos(PI/3+random(-2,2)));
                    curveVertex(size1/3+s*sin(PI*2/3),size2/3+t*cos(PI*2/3));
                    curveVertex(size1*sin(PI*4/3+random(-2,2)),size2*cos(PI*4/3+random(-2,2)));
                    curveVertex(size1*sin(PI*5/3),size2*cos(PI*5/3));
                    endShape();
                });
        
        this.theta += delta_theta * PI / 180;
        if (emotion_1.isAimAchieved() || emotion_2.isAimAchieved()||emotion_3.isAimAchieved()) {
            emotion_1.getGraphs().values().stream()
                    .map(StandardGraph::asGraphviz)
                    .forEach(System.out::println);
            emotion_2.getGraphs().values().stream()
                    .map(StandardGraph::asGraphviz)
                    .forEach(System.out::println);
            emotion_3.getGraphs().values().stream()
                    .map(StandardGraph::asGraphviz)
                    .forEach(System.out::println);
            setup();
        } else {
            emotion_1.navigate();
            emotion_2.navigate();
            emotion_3.navigate();
        }      
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        PApplet.main(Main.class);
    }
}