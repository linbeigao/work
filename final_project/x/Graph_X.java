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
 
 * This project is inspired by my teacher Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 * And I want you to know that everyone has the same feeling.
 * We suffering from depression,blue,sorrow,feeling painful and disappointed,getting anger.
 * It's ok. You are not alone. You are a good person.
 */
package art.antsomg.final_project.x;

import art.antsomg.final_project.x.Vertex_X.*;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class Graph_X extends StandardGraph<Edge_X, Vertex_X> {

    public Graph_X(double alpha, double beta) {

        super(alpha, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var spiral = Vertex_X.get(X.SPIRAL);
        var line = Vertex_X.get(X.LINE);
        var test = Vertex_X.get(X.TEST);

        this.setStart(spiral);
        var S_L = new Edge_X(spiral, line, 5.0);
        var L_S = new Edge_X(line, spiral, 1.0);
        var S_T = new Edge_X(spiral, test, 7.0);
        var T_S = new Edge_X(test, spiral, 10.0);
        var L_T = new Edge_X(line, test, 1.0);
        var T_T = new Edge_X(test, test, 100.0);
        var S_S = new Edge_X(spiral, spiral, 1.0);
        this.addEdges(
                S_L, L_S,
                S_T, T_S,
                L_T, T_T,
                S_S);
    }
}
