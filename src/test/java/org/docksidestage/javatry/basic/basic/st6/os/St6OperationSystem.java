/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 */
public class St6OperationSystem {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final String OS_TYPE_MAC = "Mac";
    private static final String OS_TYPE_WINDOWS = "Windows";
    private static final String OS_TYPE_OLD_WINDOWS = "OldWindows";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String loginId;
    private String osType;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(String loginId) {
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected String getFileSeparator() {
        if (OS_TYPE_MAC.equalsIgnoreCase(osType)) {
            return "/";
        } else if (OS_TYPE_WINDOWS.equalsIgnoreCase(osType)) {
            return "\\";
        } else if (OS_TYPE_OLD_WINDOWS.equalsIgnoreCase(osType)) {
            return "\\";
        } else {
            throw new IllegalStateException("Unknown osType: " + osType);
        }
    }

    protected String getUserDirectory() {
        if (OS_TYPE_MAC.equalsIgnoreCase(osType)) {
            return "/Users/" + loginId;
        } else if (OS_TYPE_WINDOWS.equalsIgnoreCase(osType)) {
            return "/Users/" + loginId;
        } else if (OS_TYPE_OLD_WINDOWS.equalsIgnoreCase(osType)) {
            return "/Documents and Settigs/" + loginId;
        } else {
            throw new IllegalStateException("Unknown osType: " + osType);
        }
    }
}
