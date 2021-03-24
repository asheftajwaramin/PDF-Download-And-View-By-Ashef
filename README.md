# Easy PDF Download And View management Android

## Step 1. Add the repositories in your Project level build.gradle at the end of repositories:
```
  allprojects {
		repositories {
			...
   ...
			maven { url 'https://jitpack.io' }
		}
	}
```
## Step 2. Add the dependency in your App level build.gradle:
```
dependencies {
         ...
         ...
	        implementation 'com.github.asheftajwaramin:PDF-Download-And-View-By-Ashef:0.0.1'
	}
```

# What you can do?

## Download PDF from url
```
//FileName String Should contain .pdf extention
          DownloadFromURL downloadFromURL = new DownloadFromURL(context, urlString, fileName ); 
          downloadFromURL.downloadPDF();
//Or you can use without giving a File name
          DownloadFromURL downloadFromURL = new DownloadFromURL(context, urlString); 
          downloadFromURL.downloadPDF();
```
## View PDF from online
```
	  ViewFromURL viewFromURL = new ViewFromURL(context, urlString);
                      viewFromURL.viewPDF();
```
### You can handle download complete listener also (check source MainActivity). And downloading status is already visible on Notification tray.  

## Add downloaded PDF in your ListView
```
          ShowDownloads showDownloads = new ShowDownloads(context);
          listView.setAdapter(showDownloads.pdfListAdapter());
```

### By clicking on list item you can view the pdf files also. Feel free to suggest any changes. 
