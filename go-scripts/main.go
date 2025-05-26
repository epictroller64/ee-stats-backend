package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func main() {
	fmt.Println("Hello, World!")
}

func readExcelFiles() {
	dir := "path/to/directory" // Replace with the actual directory path
	files, err := os.ReadDir(dir)
	if err != nil {
		fmt.Printf("Error reading directory: %v\n", err)
		return
	}

	for _, file := range files {
		if filepath.Ext(file.Name()) == ".xlsx" {
			filePath := filepath.Join(dir, file.Name())
			fmt.Printf("Reading file: %s\n", filePath)

			// Here you would add the code to read each Excel file
			// For example, you might use a library like "github.com/360EntSecGroup-Skylar/excelize"
			// to open and process each Excel file
		}
	}
}
