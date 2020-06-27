import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DataService} from '../data.service';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;


  constructor(private _http: HttpClient, private data: DataService) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      /**
       * Write code to get recipe
       */
      this.recipeList = [];
      this.data.getRecipesData(this.recipeValue).subscribe(response => {
        console.log(response);
        const data = response['hits'];
        data.map(r => {
          const obj = {
            name: r.recipe.label,
            url: r.recipe.url,
            icon: r.recipe.image
          };
          this.recipeList.push(obj);
        });
      });
    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      /**
       * Write code to get place
       */
      this.venueList = [];
      this.data.getVenueDetails(this.recipeValue, this.placeValue).subscribe(response => {
        const venues = response['response']['venues'].slice(0, 10);
        console.log(venues);
        venues.map(venue => {
          const venueObj = {
            name: venue.name,
            location: {
              formattedAddress: [venue.location.address, venue.location.city, venue.location.country]
            }
          };
          this.venueList.push(venueObj);
        });
      });
    }
  }
}
