from http.server import HTTPServer, SimpleHTTPRequestHandler
import mimetypes

class CustomHandler(SimpleHTTPRequestHandler):
    def end_headers(self):
        self.send_header('Access-Control-Allow-Origin', '*')
        super().end_headers()

    def guess_type(self, path):
        if path.endswith('.png'):
            return 'image/png'
        return super().guess_type(path)

if __name__ == '__main__':
    server_address = ('', 8000)
    httpd = HTTPServer(server_address, CustomHandler)
    print("Serving at port 8000...")
    httpd.serve_forever()
