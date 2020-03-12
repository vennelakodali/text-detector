import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { LoadingController } from '@ionic/angular';
import { Plugins, CameraSource, CameraResultType, CameraPhoto } from '@capacitor/core';
const { Camera, TextDetector } = Plugins;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
  @ViewChild('svgContainer', { static: false})
  svgContainer: ElementRef<SVGElement>;

  private imageFile?: CameraPhoto;

  constructor(private loadingController: LoadingController) {}

  async ngOnInit() {}

  async detectTextInImage() {
    const loader = await this.loadingController.create({
      message: 'Processing Image...',
    })

    // Using the capacitor Plugin Camera to choose a picture from the device's 'Photos'
    Camera.getPhoto({
      resultType: CameraResultType.Uri,
      source: CameraSource.Photos,
    }).then(async imageFile => {
      this.imageFile = imageFile;

      loader.present()
      await TextDetector.detectText({value: imageFile.path!});
      loader.dismiss();
    }).catch(error => console.error(error))
  }

}
