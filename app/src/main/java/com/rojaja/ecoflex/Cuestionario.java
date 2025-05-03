package com.rojaja.ecoflex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Cuestionario extends AppCompatActivity {

    private TextView questionText, resultText;
    private RadioButton option1, option2, option3;
    private RadioGroup optionsGroup;
    private Button nextButton, tipsButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private class Question {
        String questionText;
        String[] options;
        int correctIndex;

        public Question(String questionText, String[] options, int correctIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctIndex = correctIndex;
        }
    }
    private static final int REQUEST_EDUCACION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        questionText = findViewById(R.id.questionText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);
        resultText = findViewById(R.id.resultText);
        tipsButton = findViewById(R.id.tipsButton);

        loadQuestions();
        showQuestion();

        nextButton.setOnClickListener(v -> {
            if (checkAnswer()) score++;
            currentQuestionIndex++;

            if (currentQuestionIndex < questions.size()) {
                showQuestion();
            } else {
                showResult();
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cuestionario.this, educacion.class);
                startActivityForResult(intent, REQUEST_EDUCACION);

            }
        });

    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("¿Cuál de las siguientes es una alternativa para reducir el uso de plásticos de un solo uso?",
                new String[]{"Utilizar bolsas de plástico más gruesas", "Cambiar a productos reutilizables como botellas de acero inoxidable", "Quemar los residuos plásticos para generar energía"}, 1));
        questions.add(new Question("¿Qué tipo de residuos se considera parte de la 'fracción resto'?",
                new String[]{"Cáscaras de huevo y restos de comida", "Papel y cartón limpios", "Pañales, compresas y colillas"}, 1));
        questions.add(new Question("¿Cuál es el primer paso del proceso industrial de reciclaje del papel y cartón?",
                new String[]{"Clasificación y compactación en grandes cubos", "Blanqueamiento de las fibras", "Secado y bobinado del papel"}, 0));
        questions.add(new Question("¿Qué impacto ambiental negativo se menciona respecto al uso excesivo de envases de plástico?",
                new String[]{"Contribuyen a la deforestación masiva", "Aumentan la biodiversidad urbana", "Afectan negativamente al agua, flora y fauna"}, 2));
        questions.add(new Question("¿Cuál es uno de los beneficios de usar materias primas recicladas?",
                new String[]{"Aumento de dependencia de materias primas vírgenes", "Reducción de costos y mejora de reputación", "Incremento del consumo energético"}, 1));
        questions.add(new Question("¿Cuál es uno de los beneficios ambientales de utilizar bolsas reutilizables?",
                new String[]{"Generan más residuos", "Reducen la contaminación", "Incrementan el uso de recursos"}, 2));
        questions.add(new Question("¿Qué porcentaje de alimentos producidos se desperdicia anualmente?",
                new String[]{"10%", "25%", "33%"}, 2));
        questions.add(new Question("¿Cuál es una acción para evitar desperdicio de alimentos en casa?",
                new String[]{"Comprar sin planificar", "Congelar excedentes y almacenar bien", "Ignorar fechas de consumo"}, 1));
        questions.add(new Question("¿Qué residuos deben colocarse en el contenedor de restos?",
                new String[]{"Botellas de vidrio", "Residuos orgánicos compostables", "Compresas y pañales"}, 2));
        questions.add(new Question("¿Qué acción ayuda a fomentar la economía circular?",
                new String[]{"Reutilizar envases", "Usar productos desechables", "No separar residuos"}, 1));
    }

    private void showQuestion() {
        Question q = questions.get(currentQuestionIndex);
        questionText.setText(q.questionText);
        option1.setText(q.options[0]);
        option2.setText(q.options[1]);
        option3.setText(q.options[2]);
        optionsGroup.clearCheck();

        resultText.setVisibility(View.GONE);
        tipsButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        optionsGroup.setVisibility(View.VISIBLE);
    }

    private boolean checkAnswer() {
        int selected = optionsGroup.getCheckedRadioButtonId();
        if (selected == -1) return false;

        int selectedIndex = -1;
        if (selected == R.id.option1) selectedIndex = 0;
        else if (selected == R.id.option2) selectedIndex = 1;
        else if (selected == R.id.option3) selectedIndex = 2;

        return selectedIndex == questions.get(currentQuestionIndex).correctIndex;
    }

    private void showResult() {
        questionText.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        resultText.setVisibility(View.VISIBLE);

        if (score <= 4) {
            resultText.setText("No has conseguido aprobar. \nHas acertado: " +score+" pregunta/s. \nTe recomendamos que leas de nuevo los consejos de reciclaje.");
            tipsButton.setVisibility(View.VISIBLE);
        } else if (score <= 8) {
            resultText.setText("Bien, has acertado: "+score+" preguntas.\nEstás iniciándote en el reciclaje.");
        } else {
            resultText.setText("Bravo, has acertado: "+score+" preguntas.\nEres todo un experto en el reciclaje.");
        }
    }

}

