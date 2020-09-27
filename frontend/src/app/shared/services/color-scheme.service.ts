import {Injectable, Renderer2, RendererFactory2} from '@angular/core';
import {Theme} from '../models/theme';

@Injectable({
  providedIn: 'root'
})
export class ColorSchemeService {
  private renderer: Renderer2;
  private colorScheme: string;
  // Define prefix for clearer and more readable class names in scss files
  private colorSchemePrefix = 'color-scheme-';
  mainImageUrl = '../assets/alien.svg';
  conferenceComponentImageUrl = '../../../../assets/icon3-alien.svg';

  constructor(rendererFactory: RendererFactory2) {
    // Create new renderer from renderFactory, to make it possible to use renderer2 in a service
    this.renderer = rendererFactory.createRenderer(null, null);
  }

  _detectPrefersColorScheme() {
    // Detect if prefers-color-scheme is supported
    if (window.matchMedia('(prefers-color-scheme)').media !== 'not all') {
      // Set colorScheme to alien if prefers-color-scheme is alien. Otherwise set to light.
      this.colorScheme = window.matchMedia('(prefers-color-scheme: alien)').matches ? Theme.Alien : Theme.Light;
    } else {
      // If the browser doesn't support prefers-color-scheme, set it as default to alien
      this.colorScheme = Theme.Alien;
    }
  }

  _setColorScheme(scheme) {
    this.colorScheme = scheme;
    // Save prefers-color-scheme to localStorage
    localStorage.setItem('prefers-color', scheme);
  }

  _getColorScheme() {
    // Check if any prefers-color-scheme is stored in localStorage
    if (localStorage.getItem('prefers-color')) {
      // Save prefers-color-scheme from localStorage
      this.colorScheme = localStorage.getItem('prefers-color');
    } else {
      // If no prefers-color-scheme is stored in localStorage, try to detect OS default prefers-color-scheme
      this._detectPrefersColorScheme();
    }
  }

  load() {
    this._getColorScheme();
    this.renderer.addClass(document.body, this.colorSchemePrefix + this.colorScheme);

    // Set correct icon images
    this.mainImageUrl = this.currentActive() === 'alien' ? '../assets/alien.svg' : '../assets/icon1.png';
    this.conferenceComponentImageUrl = this.currentActive() === 'alien' ? '../assets/icon3-alien.svg' : '../assets/icon3-svg.svg';
  }

  update(scheme) {
    this._setColorScheme(scheme);
    // Remove the old color-scheme class
    this.renderer.removeClass(document.body, this.colorSchemePrefix + (this.colorScheme === Theme.Alien ? Theme.Light : Theme.Alien));
    // Add the new / current color-scheme class
    this.renderer.addClass(document.body, this.colorSchemePrefix + scheme);
  }

  currentActive() {
    return this.colorScheme;
  }

}
