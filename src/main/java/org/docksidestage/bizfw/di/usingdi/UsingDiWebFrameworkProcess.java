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
package org.docksidestage.bizfw.di.usingdi;

import org.docksidestage.bizfw.di.container.SimpleDiContainer;

/**
 * @author jflute
 */
public class UsingDiWebFrameworkProcess { // like mock of web framework with DI container

    // ===================================================================================
    //                                                                     Accessor Action
    //                                                                     ===============
    public void requestAccessorCallFriend() { // e.g. /accessor/callfriends/
        UsingDiAccessorAction action = prepareAccessorAction();
        action.callFriend();
    }

    public void requestAccessorGoToOffice() { // e.g. /accessor/gotooffice/
        UsingDiAccessorAction action = prepareAccessorAction();
        action.goToOffice();
    }

    private UsingDiAccessorAction prepareAccessorAction() {
        SimpleDiContainer container = SimpleDiContainer.getInstance();
        return (UsingDiAccessorAction) container.getComponent(UsingDiAccessorAction.class);
    }

    // ===================================================================================
    //                                                                   Annotation Action
    //                                                                   =================
    public void requestAnnotationCallFriend() { // e.g. /annotation/callfriends/
        UsingDiAnnotationAction action = prepareAnnotationAction();
        action.callFriend();
    }

    public void requestAnnotationGoToOffice() { // e.g. /annotation/gotooffice/
        UsingDiAnnotationAction action = prepareAnnotationAction();
        action.goToOffice();
    }

    private UsingDiAnnotationAction prepareAnnotationAction() {
        SimpleDiContainer container = SimpleDiContainer.getInstance();
        return (UsingDiAnnotationAction) container.getComponent(UsingDiAnnotationAction.class);
    }

    // ===================================================================================
    //                                                                   Delegating Action
    //                                                                   =================
    public void requestDelegatingCallFriend() { // e.g. /delegating/callfriends/
        UsingDiDelegatingAction action = prepareDelegatingAction();
        action.callFriend();
    }

    public void requestDelegatingGoToOffice() { // e.g. /delegating/gotooffice/
        UsingDiDelegatingAction action = prepareDelegatingAction();
        action.goToOffice();
    }

    private UsingDiDelegatingAction prepareDelegatingAction() {
        SimpleDiContainer container = SimpleDiContainer.getInstance();
        return (UsingDiDelegatingAction) container.getComponent(UsingDiDelegatingAction.class);
    }
}
