export class User {
    id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    mentor: boolean;
    profilePhoto: string;
}

export class UserUpdate extends User{
    file: File; 
    fileName: string;
}