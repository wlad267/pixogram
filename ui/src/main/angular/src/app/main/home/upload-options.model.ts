
export class MediaInfo {
    fileName: string; 
    title: string;
    description: string;
    
    constructor() { 
        this.fileName = ''; 
        this.title = '';
        this.description = ''; 
    } 
}

export class MediaUploadRequest extends MediaInfo { 
    file: File; 
    constructor() { 
        super();
        this.file = null;
    } 
}

export class MediaDetails extends MediaInfo { 
    uri: string;
    constructor() { 
        super();
        this.uri = null;
    } 
}


