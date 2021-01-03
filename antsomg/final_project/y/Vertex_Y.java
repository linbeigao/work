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

import java.util.HashMap;
import java.util.Map;
import tech.metacontext.ocnhfa.antsomg.impl.StandardVertex;

/**
 *
 * @author Allen-Lin <allen.killer3724@gmail.com>
 */
public class Vertex_Y extends StandardVertex {

    private static Map<Y, Vertex_Y> instances
            = new HashMap<>();

    public static enum Y {
        WHITE, BLUE, RED, YELLOW, GREEN, PURPLE, BLACK, LAKE, SUN, PINK;

        Y() {
            instances.put(this, new Vertex_Y(this));
        }
    }

    private Vertex_Y(Y name) {

        super(name.toString());
    }

    public static Vertex_Y get(Y name) {

        return instances.get(name);
    }
}
