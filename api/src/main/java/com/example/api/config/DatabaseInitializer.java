package com.example.api.config;

import java.util.ArrayList;   
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.api.model.Assunto;
import com.example.api.model.Disciplina;
import com.example.api.model.EnumRole;
import com.example.api.model.Usuario;
import com.example.api.repository.AssuntoRepository;
import com.example.api.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseInitializer implements CommandLineRunner{

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

        String senha = new BCryptPasswordEncoder().encode("pass");
        Usuario usuario = new Usuario("admin1", senha, "admin1@email.com", EnumRole.ROLE_ADMIN);
        usuario = usuarioRepository.save(usuario);
    }

}
