package org.hson.reactive.config;

import org.hson.reactive.model.User;
import org.hson.reactive.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

/**
 * Created by Hyun Woo Son on 11/8/18
 **/
@Configuration
public class RouterConfig {

    @Autowired
    private ServiceUser userService;

    @Bean
    public RouterFunction<?> helloRouterFunction(){

        return route(GET("/hello"),request ->
            ok().body(just("Hello world!"),String.class)
        ).andRoute(GET("/bye"), request ->
                ok().body(just("bye !"),String.class));
    }


    @Bean
    public RouterFunction<?> routerFunction(){

        return route(GET("/findAll"),this::findAll).andRoute(POST("/save"),this::save)
                .andRoute(GET("/byId/{id}"), request ->
                        findByIdServerResponse(Integer.parseInt(request.pathVariable("id")))
                        );

    }


    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().body(userService.getAll(), User.class);
    }


    public Mono<ServerResponse> save(ServerRequest serverRequest){
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        Flux<User> userSaved = userService.save(userMono);
        return ServerResponse.ok().body(userSaved,User.class);
    }


    public Mono<ServerResponse> findByIdServerResponse(@PathVariable int id){
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return userService.findById(id).flatMap(user -> ServerResponse.ok().header("uno","dos").body(fromObject(user))).switchIfEmpty(notFound);
    }




}
