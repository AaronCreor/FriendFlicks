import firebase_admin as fb
from firebase_admin import firestore
import pandas as pd

cred = fb.credentials.Certificate('nebulabs-001-firebase-adminsdk-nwwgu-eb5cf2f8d0.json')
default_app = fb.initialize_app(cred)
db = firestore.client()

df = pd.read_csv('IMDbMovies.csv', header=None)
dict = {}

for index, row in df.iterrows():
    dict[str(index)] = str(row[0])

doc_ref = db.collection('movieExploreList').document('items')
doc_ref.set(dict)