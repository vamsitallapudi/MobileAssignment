# Mobile Assignment R&D Test

## An App to search for cities Efficiently

### MAD Score Card For the above project:

![Summary](/assets/summary.png)</br>
![Kotlin](/assets/kotlin.png)</br>
![Jetpack](/assets/jetpack.png)</br>
![Studio](/assets/studio.png)</br>
![App Bundle](/assets/app_bundle.png)

### Data Structure used:

Here I've used Trie DS to implement efficient searching by Prefix.
<br>
 Implemented Trie because it is an efficient data structure while getting the keys with auto suggestion. Time Complexity to insert all words - O(M * N) where M is the average length of the word and N -> No of words.<br>
 Time Complexity to get each Word with prefix - 0(M) where M is the length of the word.
 <br>
 <br>
 Since we are filtering the data with prefix, Trie is the best DS to lookup compared to HashTable/HashMap. Also since large data, collisions can occur with Hashtable which again increases the Time Complexity.