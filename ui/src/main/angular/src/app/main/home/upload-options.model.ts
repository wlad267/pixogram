
export class MediaInfo {
    id?:string;
    fileName: string; 
    title: string;
    type: string;
    description: string;
    constructor() { 
        this.fileName = ''; 
        this.title = '';
        this.description = ''; 
    } 
}

export class MediaUploadRequest extends MediaInfo { 
    file: File; 
    userId: string;
    constructor() { 
        super();
        this.file = null;
    } 
}

export class MediaDetails extends MediaInfo { 
    uri: string;
    constructor(title?: string) { 
        super();
        this.uri = null;
        this.title = title;
    }     
}


