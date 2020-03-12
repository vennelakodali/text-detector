package com.bendyworks.TextDetector

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.gms.vision.text.TextRecognizer
import com.google.firebase.ml.common.FirebaseMLException
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions
import java.io.IOException


class TextTextDetector {

  fun detectObjects(context: Context, fileUri: Uri, degrees: Int) {
    val image: FirebaseVisionImage
    try {
      println("uri:" + fileUri)
      image = FirebaseVisionImage.fromFilePath(context, fileUri);

      println("image: " + image)
      val options = FirebaseVisionObjectDetectorOptions.Builder()
        .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
        .enableMultipleObjects()
        .enableClassification()  // Optional
        .build()

      val objectDetector = FirebaseVision.getInstance().getOnDeviceObjectDetector(options)

      println("detector: " + objectDetector)

      objectDetector.processImage(image)
        .addOnSuccessListener { detectedObjects ->
          println("success")
          println("detectedObjects: " + detectedObjects)

          for (obj in detectedObjects) {
            val id = obj.trackingId       // A number that identifies the object across images
            val bounds = obj.boundingBox  // The object's position in the image

            // If classification was enabled:
            val category = obj.classificationCategory
            val confidence = obj.classificationConfidence
            println("bounds: " + bounds)
          }

        }
        .addOnFailureListener { e ->
          println("error")
          println(e)
        }
    } catch (e: IOException) {
      e.printStackTrace();
    }
  }

  fun detectText(context: Context, fileUri: Uri, degrees: Int) {
    val image: FirebaseVisionImage
    try {
      println("uri:" + fileUri)
      image = FirebaseVisionImage.fromFilePath(context, fileUri);

      println("image: " + image)


      val textDetector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

      println("detector: " + textDetector)



      textDetector.processImage(image)
        .addOnSuccessListener { detectedText ->
          println("success")
          println("detectedText:" + detectedText)
          for (block in detectedText.textBlocks) {
            val boundingBox = block.boundingBox
            val cornerPoints = block.cornerPoints
            val text = block.text

            println("boundingBox: " + boundingBox)
            println("cornerPoints: " + cornerPoints)
            println("text: " + text)

//            for (line in block.lines) {
//              println("line: " + line)
//              for (element in line.elements) {
//                println("element: " + element)
//              }
//            }
          }
        }
        .addOnFailureListener { e ->
          println("error")
          println(e)
        }
    } catch (e: IOException) {
      e.printStackTrace();
    }
  }
}
