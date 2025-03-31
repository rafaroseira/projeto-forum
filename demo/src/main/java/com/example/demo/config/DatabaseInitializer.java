package com.example.demo.config;

import java.util.ArrayList;   
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.model.Category;
import com.example.demo.model.Subject;
import com.example.demo.repository.CategoryRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Subject> subjects = new ArrayList<Subject>();
        subjects.add(new Subject("Física"));
        subjects.add(new Subject("Matemática"));
        subjects.add(new Subject("Química"));
        //subjects = subjectRepository.saveAll(subjects);

        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category("Mecânica Geral","Cinemática, Dinâmica, Estática." , subjects.getFirst()));
        categories.add(new Category("Termologia","Termodinâmica, termometria, calorimetria, dilatação dos corpos etc.",subjects.getFirst()));
        categories.add(new Category("Mecânica dos Fluidos", "Hidrostática, Hidrodinâmica, Pressão dos Gases etc.", subjects.getFirst()));
        categories.add(new Category("Eletricidade e Magnetismo", "Eletrostática, Eletricidade, Eletromagnetismo etc.", subjects.getFirst()));
        categories.add(new Category("Óptica", "Óptica em geral", subjects.getFirst()));
        categories.add(new Category("Ondulatória e Movimentos Periódicos", "Fenômenos ondulatórios, MHS, som, luz etc.", subjects.getFirst()));
        categories.add(new Category("Física Moderna", "Teoria da relatividade, buracos negros etc.", subjects.getFirst()));
        categories.add(new Category("Matemática do Ensino Fundamental", "Números e expressões numéricas, conjuntos numéricos, aritmética, frações, decimais, dízimas periódicas, notação científica, múltiplos, divisores, antecessores e sucessores, fatoração, MDC, MMC, potenciação, radiciação, PA, PG, grandezas, unidades de medidas etc.", subjects.get(1)));
        categories.add(new Category("Álgebra", "Equações, inequações, sistemas, funções, polinômios, expressões algébricas etc.", subjects.get(1)));
        categories.add(new Category("Matemática Financeira", "Valor do dinheiro no tempo, capital, juros, fluxo de caixa, equivalência financeira, sistemas de amortização etc.", subjects.get(1)));
        categories.add(new Category("Probabilidades, Estatística e Análise Combinatória", "Probabilidade, fatorial, binômio de newton, permutação, combinação, arranjo, amostragem etc.", subjects.get(1)));
        categories.add(new Category("Trigonometria", "Relações métricas, razões trigonométricas, identidades trigonométricas, funções trigonométricas, ângulos notáveis etc.", subjects.get(1)));
        categories.add(new Category("Geometria Plana e Espacial", "Pontos, retas, planos, ângulos, polígonos, áreas, perímetros etc.", subjects.get(1)));
        categories.add(new Category("Geometria Analítica", "Distâncias, posições relativas, equação da reta, retas paralelas, retas perpendiculares, equação da circunferência, vetores, elipses, hipérboles, parábolas etc.", subjects.get(1)));
        categories.add(new Category("Iniciação ao Cálculo", "Limites, derivadas, integrais etc.", subjects.get(1)));
        categories.add(new Category("Química Geral e Inorgânica", "Tabela periódica, elementos químicos, átomos, ligações químicas, estados físicos, geometria molecular, substâncias, misturas, reações químicas, combustão, densidade etc.", subjects.get(2)));
        categories.add(new Category("Química Orgânica", "Hidrocarbonetos, alcoóis, fenóis, ácido carboxílico, aldeídos, cetonas, éster, éter, haletos orgânicos, haletos de ácidos, aminas, amidas, nitrocompostos, nitrilas, ácido sulfônico, composto de Grignard etc.", subjects.get(2)));
        categories.add(new Category("Físico-Química", "Estudo dos gases, teoria das soluções, equilíbrio químico, cinética química, eletroquímica, mecânica quântica, termodinâmica química, propriedades coligativas, coloides, polímeros, química nuclear etc.", subjects.get(2)));
        categories = categoryRepository.saveAll(categories);
    }

}

