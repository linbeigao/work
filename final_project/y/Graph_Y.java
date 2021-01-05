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
package art.antsomg.final_project.y;

import static art.antsomg.final_project.y.Vertex_Y.Y.*;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class Graph_Y extends StandardGraph<Edge_Y, Vertex_Y> {

    public Graph_Y(double alpha, double beta) {

        super(alpha, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var white = Vertex_Y.get(WHITE);
        var blue = Vertex_Y.get(BLUE);
        var red = Vertex_Y.get(RED);
        var yellow = Vertex_Y.get(YELLOW);
        var green = Vertex_Y.get(GREEN);
        var purple = Vertex_Y.get(PURPLE);
        var black = Vertex_Y.get(BLACK);
        var lake = Vertex_Y.get(LAKE);
        var sun = Vertex_Y.get(SUN);
        var pink =Vertex_Y.get(PINK);

        this.setStart(white);

        var w_w = new Edge_Y(white, white, 1.0);
        var b_b = new Edge_Y(blue, blue, 1.0);
        var r_r = new Edge_Y(red, red, 1.0);
        var y_y = new Edge_Y(yellow, yellow, 1.0);
        var g_g = new Edge_Y(green, green, 1.0);
        var p_p = new Edge_Y(purple, purple, 1.0);
        var r_p = new Edge_Y(red, purple, 7.0);
        var p_r = new Edge_Y(purple, red, 7.0);
        var b_p = new Edge_Y(blue, purple, 7.0);
        var p_b = new Edge_Y(purple, blue, 7.0);
        var p_y = new Edge_Y(purple, yellow, 3.5);
        var y_p = new Edge_Y(yellow, purple, 3.5);
        var p_bl = new Edge_Y(purple, black, 9.0);
        var bl_p = new Edge_Y(black, purple, 15.0);
        var w_b = new Edge_Y(white, blue, 10.0);
        var b_w = new Edge_Y(blue, white, 10.0);
        var w_r = new Edge_Y(white, red, 10.0);
        var r_w = new Edge_Y(red, white, 10.0);
        var w_y = new Edge_Y(white, yellow, 8.0);
        var y_w = new Edge_Y(yellow, white, 8.0);
        var w_g = new Edge_Y(white, green, 8.0);
        var g_w = new Edge_Y(green, white, 8.0);
        var bl_bl = new Edge_Y(black, black, 3.0);
        var l_l = new Edge_Y(lake, lake, 1.5);
        var g_l = new Edge_Y(green, lake, 3.0);
        var l_g = new Edge_Y(lake, green, 7.0);
        var s_s = new Edge_Y(sun, sun, 2.5);
        var w_s = new Edge_Y(white, sun, 1.5);
        var s_w = new Edge_Y(sun, white, 7.5);
        var s_r = new Edge_Y(sun, red, 10.5);
        var r_s = new Edge_Y(red, sun, 2.0);
        var p_pi = new Edge_Y(purple, pink, 10.0);
        var pi_p = new Edge_Y(pink, purple, 7.0);
        var pi_pi = new Edge_Y(pink, pink, 7.0);
        var r_pi = new Edge_Y(red, pink, 15.0);
        var pi_r = new Edge_Y(pink, red, 100.0);
        
        this.addEdges(
                w_w, b_b, r_r, y_y, g_g, p_p,
                w_b, b_w, r_p, p_r, b_p, p_b,
                w_r, r_w, w_g, g_w, p_y, y_p,
                w_y, y_w, p_bl, bl_p, l_l, g_l, l_g, s_s, w_s, s_w, s_r, r_s,
                bl_bl, pi_pi, r_pi, pi_r, pi_p, p_pi
        );
    }

}
