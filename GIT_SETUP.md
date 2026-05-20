# Git Setup Instructions

## Prerequisites
Ensure Git is installed on your system. Download from: https://git-scm.com/download/win

## Steps to Push to GitHub/GitLab

### 1. Initialize Repository Locally
```powershell
cd c:\Users\cse24-112\AccommodationFinder
git init
```

### 2. Configure Git (First Time Only)
```powershell
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 3. Add All Files
```powershell
git add .
```

### 4. Make Initial Commit
```powershell
git commit -m "Initial commit: Accommodation Finder Android App

- Complete user authentication system
- 50 accommodation listings with smart filtering
- Payment simulation with reference generation
- Chat functionality between students and landlords
- Room Database for persistent storage
- Material Design UI with ConstraintLayout"
```

### 5. Create Remote Repository

**For GitHub:**
- Go to https://github.com/new
- Create new repository "AccommodationFinder"
- Copy the repository URL

**For GitLab:**
- Go to https://gitlab.com/projects/new
- Create new project "AccommodationFinder"
- Copy the repository URL

### 6. Add Remote and Push
```powershell
git remote add origin https://github.com/YOUR_USERNAME/AccommodationFinder.git
git branch -M main
git push -u origin main
```

### 7. Verify
```powershell
git log
git remote -v
```

## Alternative: Quick Setup Script

Copy and run this complete script in PowerShell:

```powershell
cd c:\Users\cse24-112\AccommodationFinder
git init
git config user.name "Your Name"
git config user.email "your.email@example.com"
git add .
git commit -m "Initial commit: Accommodation Finder Android App with full features"
git branch -M main
echo "Repository initialized. Now run: git remote add origin <REPO_URL>"
echo "Then run: git push -u origin main"
```

## Files Tracked
- All Kotlin source code (.kt)
- All XML layouts
- Configuration files (build.gradle.kts, gradle.properties, etc.)
- AndroidManifest.xml
- Dependencies and project structure

## Ignored Files
- Build outputs and artifacts
- IDE configuration (.idea, .iml)
- Gradle cache
- Local properties
- APK files

## Project is Ready!
✅ All code is committed-ready
✅ .gitignore is configured for Android
✅ Clean project structure
✅ No build artifacts included
