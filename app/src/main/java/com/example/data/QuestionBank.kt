package com.example.data

class QuestionBank()
{
    private val images: HashMap<String, RadImage> = ImageBank().images
    val questions: MutableList<Question> = mutableListOf()


    init{
        questions.add(
            Question(
                images.getValue("normal_chest_x_ray_1"),
                questionText = "What condition presents the image?",
                "Mycoplasmatic Pneumonia", "Pulmonary tuberculosis", "pleural effusion"
            )
        )
        questions.add(
            Question(
                images.getValue("bronchopneumonia"),
                questionText = "What condition presents the image?",
                "Normal chest X-ray", "Pulmonary Tuberculosis", "Lobar pneumococcal pneumonia"
            )
        )
        questions.add(
            Question(
                images.getValue("pulmonary_tuberculosis_35"),
                questionText = "What condition presents the image?",
                "Normal chest X-ray", "Bronchopneumonia", "Lobar pneumococcal pneumonia"
            )
        )

        questions.add(
            Question(
                images.getValue("pneumococcal_pneumonia"),
                questionText = "What condition presents the image?",
                "Normal chest X-ray", "Bronchopneumonia", "Pulmonary Tuberculosis"
            )
        )

        questions.add(
            Question(
                images.getValue("mycoplasma_pneumoniae_pneumonia"),
                questionText = "What condition presents the image?",
                "Normal chest X-ray", "Bronchopneumonia", "Pulmonary Tuberculosis"
            )
        )
    }

}
