from PIL import Image

# Create a simple RGB image (100x100 pixels, red)
image = Image.new('RGB', (100, 100), color = 'red')

# Save as a valid PNG
image.save('valid.png')
print("valid.png generated successfully.")
