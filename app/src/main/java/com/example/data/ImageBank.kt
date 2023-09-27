package com.example.data

import androidx.compose.ui.res.painterResource
import com.example.radimagequiz.R

class ImageBank (){
    val images: HashMap<String ,RadImage> = HashMap<String, RadImage>()


    init {
        images["normal_chest_x_ray_1"] = RadImage(
            imageId = R.drawable.normal_chest_x_ray_1,
            license = "Case courtesy of Henry Knipe, Radiopaedia.org, rID: 31525",
            forOnlineUse = "Case courtesy of Henry Knipe, <a href=\"https://radiopaedia.org/?lang=us\">Radiopaedia.org</a>. From the case <a href=\"https://radiopaedia.org/cases/31525?lang=us\">rID: 31525</a>",
            description = "Normal chest x-ray"
        )
        images["bronchopneumonia"] = RadImage(
            imageId = R.drawable.bronchopneumonia,
            license = "Case courtesy of Henry Knipe, Radiopaedia.org, rID: 49869",
            forOnlineUse = "Case courtesy of Henry Knipe, <a href=\"https://radiopaedia.org/?lang=us\">Radiopaedia.org</a>. From the case <a href=\"https://radiopaedia.org/cases/49869?lang=us\">rID: 49869</a>",
            description = "Bronchopneumonia"
        )
        images["pulmonary_tuberculosis_35"] = RadImage(
            imageId = R.drawable.pulmonary_tuberculosis_35,
            license = "Case courtesy of Prashant Mudgal, Radiopaedia.org, rID: 51361",
            forOnlineUse = "Case courtesy of Prashant Mudgal, <a href=\"https://radiopaedia.org/?lang=us\">Radiopaedia.org</a>. From the case <a href=\"https://radiopaedia.org/cases/51361?lang=us\">rID: 51361</a>",
            description = "Pulmonary Tuberculosis",
            longerDescription = "A thick walled cavity noted in the left lower zone with adjacent ground glass nodules and opacities."
        )
        images["pneumococcal_pneumonia"] = RadImage(
            imageId = R.drawable.pneumococcal_pneumonia,
            license = "Case courtesy of Jeremy Jones, Radiopaedia.org, rID: 13553",
            forOnlineUse = "Case courtesy of Jeremy Jones, <a href=\"https://radiopaedia.org/?lang=us\">Radiopaedia.org</a>. From the case <a href=\"https://radiopaedia.org/cases/13553?lang=us\">rID: 13553</a>",
            description = "Lobar pneumoccoccal pneumonia",
            longerDescription = "Right upper lobe dense consolidation."
        )
        images["mycoplasma_pneumoniae_pneumonia"] = RadImage(
            imageId = R.drawable.mycoplasma_pneumoniae_pneumonia,
            license = "Case courtesy of Yaïr Glick, Radiopaedia.org, rID: 92316",
            forOnlineUse = "Case courtesy of Yaïr Glick, <a href=\"https://radiopaedia.org/?lang=us\">Radiopaedia.org</a>. From the case <a href=\"https://radiopaedia.org/cases/92316?lang=us\">rID: 92316</a>",
            description = "Mycoplasma pneumoniae pneumonia",
            longerDescription = "Bilateral perihilar interstitial infiltrates. Dense medial opacity in the right upper zone causing thickening of the right paratracheal stripe representing atelectasis in the right upper lobe."
        )



    }
}