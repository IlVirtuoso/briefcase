/*
 * Copyright (C) 2019 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.opendatakit.briefcase.push.central;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UncheckedIOException;

class CentralErrorMessage {
  final String message;

  CentralErrorMessage(String message) {
    this.message = message;
  }

  public static CentralErrorMessage empty() {
    return new CentralErrorMessage("");
  }
  public static CentralErrorMessage parseErrorResponse(String errorResponse) {
    if (errorResponse.isEmpty())
      return CentralErrorMessage.empty();
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readTree(errorResponse);
      String message = jsonNode.get("message").asText();

      return new CentralErrorMessage(message);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
