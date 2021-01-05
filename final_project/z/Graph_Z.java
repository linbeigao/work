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
package art.antsomg.final_project.z;

import art.antsomg.final_project.z.Vertex_Z.*;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class Graph_Z extends StandardGraph<Edge_Z, Vertex_Z> {

    public Graph_Z(double gamma, double beta) {

        super(gamma, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var big = Vertex_Z.get(Z.BIG);
        var small = Vertex_Z.get(Z.SMALL);

        this.setStart(big);
        var B_B = new Edge_Z(big, big, 100.0);
        var S_B = new Edge_Z(small, big, 5.0);
        var B_S = new Edge_Z(big, small, 5.0);
       
        this.addEdges(
                B_B, S_B,
                B_S);
    }
}
