declare module "@capacitor/core" {
  interface PluginRegistry {
    TextDetector: TextDetectorPlugin;
  }
}

export interface TextDetectorPlugin {
  detectText(options: { value: string }): Promise<{value: string}>;
}
