package com.example.api.config;

import java.util.ArrayList;
import java.util.Arrays;    
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.api.model.Assunto;
import com.example.api.model.Comentario;
import com.example.api.model.Disciplina;
import com.example.api.model.EnumRole;
//import com.example.api.model.Role;
import com.example.api.model.Topico;
import com.example.api.model.Usuario;
import com.example.api.repository.AssuntoRepository;
//import com.example.api.repository.DisciplinaRepository;
//import com.example.api.repository.RoleRepository;
import com.example.api.repository.TopicoRepository;
import com.example.api.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseInitializer implements CommandLineRunner{

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    /*@Autowired
    private RoleRepository roleRepository;*/

    /*@Autowired
    private DisciplinaRepository disciplinaRepository;*/

    @Override
    public void run(String... args) throws Exception {

        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        disciplinas.add(new Disciplina("Física"));
        disciplinas.add(new Disciplina("Matemática"));
        disciplinas.add(new Disciplina("Química"));
        disciplinas.add(new Disciplina("Geral"));
        //disciplinas = disciplinaRepository.saveAll(disciplinas);

        List<Assunto> assuntos = new ArrayList<Assunto>();
        assuntos.add(new Assunto("Mecânica Geral","Cinemática, Dinâmica, Estática." , disciplinas.getFirst()));
        assuntos.add(new Assunto("Termologia","Termodinâmica, termometria, calorimetria, dilatação dos corpos etc.",disciplinas.getFirst()));
        assuntos.add(new Assunto("Mecânica dos Fluidos", "Hidrostática, Hidrodinâmica, Pressão dos Gases etc.", disciplinas.getFirst()));
        assuntos.add(new Assunto("Eletricidade e Magnetismo", "Eletrostática, Eletricidade, Eletromagnetismo etc.", disciplinas.getFirst()));
        assuntos.add(new Assunto("Óptica", "Óptica em geral", disciplinas.getFirst()));
        assuntos.add(new Assunto("Ondulatória e Movimentos Periódicos", "Fenômenos ondulatórios, MHS, som, luz etc.", disciplinas.getFirst()));
        assuntos.add(new Assunto("Física Moderna", "Teoria da relatividade, buracos negros etc.", disciplinas.getFirst()));
        assuntos.add(new Assunto("Matemática do Ensino Fundamental", "Números e expressões numéricas, conjuntos numéricos, aritmética, frações, decimais, dízimas periódicas, notação científica, múltiplos, divisores, antecessores e sucessores, fatoração, MDC, MMC, potenciação, radiciação, PA, PG, grandezas, unidades de medidas etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Álgebra", "Equações, inequações, sistemas, funções, polinômios, expressões algébricas etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Matemática Financeira", "Valor do dinheiro no tempo, capital, juros, fluxo de caixa, equivalência financeira, sistemas de amortização etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Probabilidades, Estatística e Análise Combinatória", "Probabilidade, fatorial, binômio de newton, permutação, combinação, arranjo, amostragem etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Trigonometria", "Relações métricas, razões trigonométricas, identidades trigonométricas, funções trigonométricas, ângulos notáveis etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Geometria Plana e Espacial", "Pontos, retas, planos, ângulos, polígonos, áreas, perímetros etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Geometria Analítica", "Distâncias, posições relativas, equação da reta, retas paralelas, retas perpendiculares, equação da circunferência, vetores, elipses, hipérboles, parábolas etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Iniciação ao Cálculo", "Limites, derivadas, integrais etc.", disciplinas.get(1)));
        assuntos.add(new Assunto("Química Geral e Inorgânica", "Tabela periódica, elementos químicos, átomos, ligações químicas, estados físicos, geometria molecular, substâncias, misturas, reações químicas, combustão, densidade etc.", disciplinas.get(2)));
        assuntos.add(new Assunto("Química Orgânica", "Hidrocarbonetos, alcoóis, fenóis, ácido carboxílico, aldeídos, cetonas, éster, éter, haletos orgânicos, haletos de ácidos, aminas, amidas, nitrocompostos, nitrilas, ácido sulfônico, composto de Grignard etc.", disciplinas.get(2)));
        assuntos.add(new Assunto("Físico-Química", "Estudo dos gases, teoria das soluções, equilíbrio químico, cinética química, eletroquímica, mecânica quântica, termodinâmica química, propriedades coligativas, coloides, polímeros, química nuclear etc.", disciplinas.get(2)));
        assuntos.add(new Assunto("Comunicados", "Fique por dentro das últimas atualizações do fórum.", disciplinas.getLast()));
        assuntos.add(new Assunto("Instruções de utilização", "Veja aqui como você pode postar e utilizar os recursos do fórum. Saiba como fazer postagens inteligíveis e objetivas.", disciplinas.getLast()));
        assuntos = assuntoRepository.saveAll(assuntos);

        /*List<Role> roles = new ArrayList<Role>();
        roles.add(new Role(EnumRole.ROLE_ADMIN));
        roles.add(new Role(EnumRole.ROLE_USER));*/
        //roles = roleRepository.saveAll(roles);

        String senha = new BCryptPasswordEncoder().encode("pass");
        Usuario usuario = new Usuario("admin1", senha, "admin1@email.com",EnumRole.ROLE_ADMIN);
        usuario = usuarioRepository.save(usuario);


        List<Topico> topicos = new ArrayList<Topico>();
        topicos.add(new Topico(
            "MCU Cinemática",
            "O sistema de marchas de uma bicicleta é utilizado para tornar o movimento mais efetivo, ora aumentando, ora diminuindo o esforço. Considere um esquema rudimentar do sistema de marchas de uma bicicleta que possui uma coroa (onde estão os pedais), de raio 20 cm, e três catracas, fixas na roda traseira: A (grande) de raio 15 cm, B (média) de raio 10 cm e C (pequena) de raio 6 cm. Inicialmente a corrente está engatada na catraca B e a bicicleta move-se com uma velocidade escalar constante. Considerando o exposto, avalie as seguintes afirmações. I. Se a frequência de pedaladas for de 120 rpm e o engate for na catraca B, a frequência de rotação da roda traseira será de 240 rpm. II. Ao mudar a corrente da catraca B para a catraca A, mantendo a frequência de pedaladas em 120 rpm, a velocidade escalar da bicicleta irá aumentar. III. Com frequência de pedaladas igual a 120 rpm e engatada na catraca C, o período de rotação da roda traseira será igual a 0,15 s. É CORRETO apenas o que se afirma em a) I, II e III. b) II e III. c) I e III. d) III. e) II\r\n" + //
                                "\r\n" + //
                                "Por que a afirmação 3 está correta?\r\n" + //
                                "\r\n" + //
                                "Gabarito : c) I e III", 
            "https://i.servimg.com/u/f78/20/59/50/24/captur11.jpg", 
            assuntos.getFirst(),
            usuario, 
            null));
        topicos.add(new Topico("UFPR 2004", "Enunciado criativo",null,assuntos.get(3), usuario,null));
        topicos.add(new Topico("UFMG", "Qual o valor de x?", null,assuntos.get(3), usuario,null));
        
        Comentario comentario1 = new Comentario(
            "Etiam accumsan, nulla sed tempor pulvinar, libero nunc sodales ipsum, sed convallis ligula lectus in quam. Aenean orci mi, mollis eu egestas imperdiet, condimentum non lectus. Integer semper risus non urna semper scelerisque.", 
            "https://2img.net/u/2713/85/25/58/avatars/248017-89.png", 
            usuario,
            topicos.getFirst());
        Comentario comentario2 = new Comentario(
            "A velocidade tangencial da corrente é constante, para coroa e catraca:\r\n" + //
                                "\r\n" + //
                                "v(co) = v(ca) ---> w(co).r(co) = w(ca).r(ca) ---> 2.pi.f(co).r(co) = 2.pi.f(ca).r(ca) ---> f(c0).r(co) = f(ca).r(ca)\r\n" + //
                                "\r\n" + //
                                "Calcule I e II\r\n" + //
                                "\r\n" + //
                                "III) r(co) = 20 cm ---> f(co) = 120 rpm = 2 rps = 2 Hz ---> Catraca C : r(ca) = 6 cm --->\r\n" + //
                                "\r\n" + //
                                "Período ---> T = 1/f --> T(co) = 1/f(co) ---> T(ca) = 1/f(ca) ---> Calcule T(ca)", 
            null,
            usuario, 
            topicos.getFirst());
        Comentario comentario3 = new Comentario(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet elit eget massa vulputate, id porttitor elit tempus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris auctor lorem et eros feugiat pretium. Cras eleifend ornare ante at tristique. Nam dictum ex eget massa sollicitudin congue. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec et nulla sit amet eros efficitur commodo. Fusce ut risus massa.", 
            null,
            usuario, 
            topicos.getFirst());

        topicos.getFirst().setComentarios(Arrays.asList(comentario1,comentario2,comentario3));
        topicos = topicoRepository.saveAll(topicos);
    }

}
