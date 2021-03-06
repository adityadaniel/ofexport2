/*
 * Copyright 2015 Paul Sidnell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.psidnell.omnifocus.format;

import java.io.IOException;
import java.io.Writer;

import org.psidnell.omnifocus.model.Node;
import org.psidnell.omnifocus.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author psidnell
 *
 * Formats the node by dumping the entire tree with Jackson.
 *
 */
public class JSONFormatter implements Formatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONFormatter.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void format(Node node, Writer out) throws IOException {
        LOGGER.info("Formatting: {}", node);
        // Mapper closes the stream - don't want that here
        Writer closeProofWriter = IOUtils.closeProofWriter(out);
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(closeProofWriter, node);
    }
}
