/* MIT License

Copyright (c) 2019 simplyatul

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

package org.simplyatul.cassandrademo.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.simplyatul.cassandrademo.constants.RestEndpoint;
import org.simplyatul.cassandrademo.service.DemoService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
@RequestMapping(RestEndpoint.API_PREFIX)
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    /*
     * Request to store data
     */
    @PostMapping(path = { RestEndpoint.API_VERSION + RestEndpoint.PARAMS},
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?>
    storeData(@RequestBody JsonNode inRequest)
            throws JsonParseException, JsonMappingException, IOException {

        logger.trace("DemoController Post request content : " + inRequest);
        return demoService.storeData(inRequest);
	}

    /*
     * Request to retrieve data
     */
    @GetMapping(path = { RestEndpoint.API_VERSION + RestEndpoint.PARAMS },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?>
        retrieveData(@Valid @RequestParam(name = "guid") String guid)
                throws IOException  {

        logger.debug("Guid : " + guid);
        return demoService.retrieveData(guid);
    }
}

