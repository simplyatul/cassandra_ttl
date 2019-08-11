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

package org.simplyatul.cassandrademo.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Configuration
@ConfigurationProperties("cassandra")
@PropertySource(value = "${cassandra.config.file}" )
@Validated
@Data
public class CassandraProperties {

    @NotNull
    private String contactpoints;

    @NotNull
    private Integer port;

    @NotNull
    private String keyspace;

    @NotNull
    @Min(value = 0, message =  "Read/Write Consistency Level should be between 0-10")
    @Max(value = 10, message = "Read/Write Consistency Level should be between 0-10")
    private Integer demodatawcl;

    @NotNull
    @Min(value = 8, message =  "GUID Length should be between 8-36")
    @Max(value = 36, message = "GUID Length should be between 8-36")
    private Integer guidlen;

/*
 Captured from com.datastax.driver.core.ConsistencyLevel
  ANY(0),
  ONE(1),
  TWO(2),
  THREE(3),
  QUORUM(4),
  ALL(5),
  LOCAL_QUORUM(6),
  EACH_QUORUM(7),
  SERIAL(8),
  LOCAL_SERIAL(9),
  LOCAL_ONE(10);
 */

    public String getCL(Integer cl) {
        String scl = "ONE"; //default

        switch (cl) {
            case 0: scl = "ANY"; break;
            case 1: scl = "ONE"; break;
            case 2: scl = "TWO"; break;
            case 3: scl = "THREE"; break;
            case 4: scl = "QUORUM"; break;
            case 5: scl = "ALL"; break;
            case 6: scl = "LOCAL_QUORUM"; break;
            case 7: scl = "EACH_QUORUM"; break;
            case 8: scl = "SERIAL"; break;
            case 9: scl = "LOCAL_SERIAL"; break;
            case 10: scl = "LOCAL_ONE"; break;

        }
        return scl.toString();
    }
}
