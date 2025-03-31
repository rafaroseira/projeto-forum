package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.config.SecurityConfig;
import com.example.demo.dto.BasicTopicInfoDTO;
import com.example.demo.dto.CreateTopicDTO;
import com.example.demo.dto.PostCommentDTO;
import com.example.demo.dto.TopicDTO;
import com.example.demo.dto.TopicPreviewDTO;
import com.example.demo.service.CommentService;
import com.example.demo.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/topics")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "topic", description = "controlador usado para operações relacionadas aos tópicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @PostMapping("/create-topic")
    @Operation(summary = "cria um tópico", description = "permite a um usuário autenticado criar um tópico associado a uma determinada categoria de assunto com título e uma mensagem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<String> createTopic(@RequestBody @Valid CreateTopicDTO dto) {
        topicService.createTopic(dto);
        
        return ResponseEntity.ok().body("Tópico criado com sucesso!");
    }
    
    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @PostMapping("/post-comment")
    @Operation(summary = "posta um comentário em um tópico", description = "permite a um usuário autenticado postar um comentário em um tópico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<?> postComment(@RequestBody @Valid PostCommentDTO dto) {
        commentService.postComment(dto);
        return ResponseEntity.ok().build(); //em dúvida sobre o response entity
    }

    @GetMapping("/{id}")
    @Operation(summary = "retorna um determinado tópico", description = "o id do tópico é passado diretamente ao endpoint e são retornadas todas as informações do tópico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)))
    })
    public TopicDTO getTopic(@PathVariable int id) {
        return topicService.getTopic(id);
    }
    
    @GetMapping("/category/{id}")
    @Operation(summary = "busca os tópicos pertencentes a uma determinada categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)))
    })
    public List<TopicPreviewDTO> getPreviews(@PathVariable int id) {
        return topicService.getPreviews(id);
    }
    
    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @GetMapping("/my-topics")
    @Operation(summary = "retorna todos os tópicos de um usuário autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)))
    })
    public List<BasicTopicInfoDTO> getMyTopics() {
        return topicService.getMyTopics();
    }
    
    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @DeleteMapping("/{id}")
    @Operation(summary = "exclui um tópico", description = "o id do tópico é passado diretamente ao endpoint e o tópico é excluído se pertencer ao usuário que está atualmente autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<String> deleteTopic(@PathVariable int id){
        topicService.deleteTopic(id);
        return ResponseEntity.ok().body("Tópico excluído com sucesso!");
    }
}
