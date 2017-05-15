from flask import Flask
from flask_restful import Api
from restful_modules import fb_macro

app = Flask(__name__)
api = Api(app)

api.add_resource(fb_macro.Account, '/account')
api.add_resource(fb_macro.Friend, '/friend')
api.add_resource(fb_macro.Message, '/message')

if __name__ == '__main__':
    app.run(port=82)
    # app.run(host='172.31.12.119', port=82)
