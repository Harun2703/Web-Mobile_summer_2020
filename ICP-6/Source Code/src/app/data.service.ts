import { Injectable } from '@angular/core';
import { environment } from './../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private FSAPPID = environment.FS_Client_ID;
  private FSAPPKEY = environment.FS_Client_Secret;
  private FOODAPPID = environment.EDAMAM_ID;
  private FOODAPPKEY = environment.EDAMAM_KEY;

  private foodDataUrl = 'https://api.edamam.com/search?app_id=' + this.FOODAPPID + '&app_key=' + this.FOODAPPKEY;
  // tslint:disable-next-line:max-line-length
  private restaurantDataUrl = 'https://api.foursquare.com/v2/venues/search?v=20180323&client_id=' + this.FSAPPID + '&client_secret=' + this.FSAPPKEY;

  constructor(private http: HttpClient) { }

  public getRecipesData(recipeName: string) {
    const apiUrl = this.foodDataUrl + `&q=${recipeName}`;
    return this.http.get(apiUrl);
  }

  public getVenueDetails(item: string, place: string) {
    const apiUrl = this.restaurantDataUrl + `&near=${place}&query=${item}`;
    return this.http.get(apiUrl);
  }
}
