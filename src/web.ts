import { WebPlugin } from '@capacitor/core';
import { TextDetectorPlugin } from './definitions';

export class TextDetectorWeb extends WebPlugin implements TextDetectorPlugin {
  constructor() {
    super({
      name: 'TextDetector',
      platforms: ['web']
    });
  }

  async detectText(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return {value: options.value+'-----fdsfjdshk'};
  }
}

const TextDetector = new TextDetectorWeb();

export { TextDetector };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(TextDetector);
