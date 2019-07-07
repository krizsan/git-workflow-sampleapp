
open module se.ivankrizsan.spring.hellowebapp {
    requires reactor.core;
    requires spring.web;
    requires spring.context;
    requires spring.webflux;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    exports se.ivankrizsan.spring.hellowebapp to spring.beans, spring.context;
}