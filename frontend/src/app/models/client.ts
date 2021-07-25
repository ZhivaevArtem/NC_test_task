export interface Client {
  id: string;
  name: string;
  surname: string;
  patronymic: string;
  birth: Date;
  address: string;
  phone: string;
  email: string;
  password: string;
  // TODO: implement images
  picUrl: string;
}
