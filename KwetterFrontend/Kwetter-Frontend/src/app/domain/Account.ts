export class Account {
  id: String;
  bio: String;
  email: String;
  followers: String[];
  follwing: String[];
  fullName: String;
  location: String;
  profileImage: String;
  web: String;
  username: String;


  constructor() {
    if(this.profileImage === undefined) {
      this.profileImage = '../../assetns/profile.jpg';
    }
  }

}
