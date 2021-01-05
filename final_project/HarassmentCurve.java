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
package art.antsomg.final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import art.antsomg.final_project.x.Edge_X;
import art.antsomg.final_project.x.Vertex_X;
import art.antsomg.final_project.y.Edge_Y;
import art.antsomg.final_project.y.Vertex_Y;
import art.antsomg.final_project.z.Edge_Z;
import art.antsomg.final_project.z.Vertex_Z;
import tech.metacontext.ocnhfa.antsomg.impl.StandardMove;
import tech.metacontext.ocnhfa.antsomg.model.Ant;
import tech.metacontext.ocnhfa.antsomg.model.Vertex;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class HarassmentCurve implements Ant<HarassmentTrace> {

    HarassmentTrace currentTrace;
    List<HarassmentTrace> route;
    private boolean completed;

    public HarassmentCurve(Vertex_X x, Vertex_Y y, Vertex_Z z) {

        this.currentTrace = new HarassmentTrace(
                new StandardMove<>(new Edge_X(x)),
                new StandardMove<>(new Edge_Y(y)),
                new StandardMove<>(new Edge_Z(z))
                
        );
        this.route = new ArrayList<>();
    }

    boolean isBalanced() {

        var count = route.stream()
                .map(trace -> trace.getDimension("x"))
                .collect(Collectors.groupingBy(Vertex::getName,
                        Collectors.counting()));
        var bang = route.stream()
                .map(trace -> trace.getDimension("z"))
                .collect(Collectors.groupingBy(Vertex::getName,
                        Collectors.counting()));
        
        if (count.size() == 3) {
            if (count.get("TEST") > (count.get("LINE") + count.get("SPIRAL") )
                    && Math.random() > 0.5) {
                return true;
            }
            if (count.get("SPIRAL") + count.get("TEST") + count.get("LINE") > 1000) {
                return true;
            }
        }
        
        else if (bang.size() == 2) {
            if (bang.get("BIG")/100 > bang.get("SMALL") ) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public List<HarassmentTrace> getRoute() {

        return this.route;
    }

    @Override
    public void addCurrentTraceToRoute() {

        this.route.add(this.currentTrace);
    }

    @Override
    public HarassmentTrace getCurrentTrace() {

        return this.currentTrace;
    }

    @Override
    public void setCurrentTrace(HarassmentTrace trace) {

        if (Objects.nonNull(this.currentTrace)) {
            this.addCurrentTraceToRoute();
        }
        this.currentTrace = trace;
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

    Object limit(int ant_population) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
