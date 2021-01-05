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

import art.antsomg.final_project.x.Edge_X;
import art.antsomg.final_project.y.Edge_Y;
import art.antsomg.final_project.z.Edge_Z;
import tech.metacontext.ocnhfa.antsomg.impl.StandardMove;
import tech.metacontext.ocnhfa.antsomg.model.Trace;
import tech.metacontext.ocnhfa.antsomg.model.Vertex;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class HarassmentTrace implements Trace {

    private final StandardMove<Edge_X> x;
    private final StandardMove<Edge_Y> y;
    private final StandardMove<Edge_Z> z;

    public HarassmentTrace(
            StandardMove<Edge_X> x,
            StandardMove<Edge_Y> y,
            StandardMove<Edge_Z> z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Vertex getDimension(String dimension) {

        return switch (dimension) {
            case "x" ->
                this.x.getSelected().getTo();
            case "y" ->
                this.y.getSelected().getTo();
            case "z" ->
                this.z.getSelected().getTo();
            default ->
                null;
        };
    }

    public StandardMove<Edge_X> getX() {

        return this.x;
    }

    public StandardMove<Edge_Y> getY() {

        return this.y;
    }
    
    public StandardMove<Edge_Z> getZ() {

        return this.z;
    }
    
    @Override
    public String toString() {

        return String.format("DemoTrace{%s, %s}",
                x.getSelected().getTo(),
                y.getSelected().getTo(),
                z.getSelected().getTo());
    }

}
