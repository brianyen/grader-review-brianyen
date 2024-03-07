CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission 2> grading-area/clone-output.txt
echo 'Finished cloning'

if [[ -f student-submission/ListExamples.java ]]
then
    echo 'Necessary files found'

    cp student-submission/ListExamples.java TestListExamples.java grading-area
    cp -r lib grading-area
else
    echo "Missing ListExamples.java file. Have you checked its name and path?"
    exit 1
fi

cd grading-area

javac -cp $CPATH *.java

if [[ $? -ne 0 ]]
then
    echo "The code failed to compile! See error above."
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-output.txt

OUTPUT=$(cat test-output.txt | tail -n 2 | head -n 1)

if [[ $(echo $OUTPUT | cut -d " " -f 1) == "OK" ]]
then
    echo "All $(echo $OUTPUT | cut -d "(" -f 2 | cut -d " " -f 1) Tests Passed! Grade: 100%"
else
    TOTAL=$(echo $OUTPUT | cut -d " " -f 3 | cut -d "," -f 1)
    FAILED=$(echo $OUTPUT | cut -d " " -f 5)
    PASSED=$(($TOTAL - $FAILED))
    echo "$PASSED/$TOTAL Tests Passed. Grade: $(echo $PASSED/$TOTAL*100 | bc -l | head -c 5)%"
fi

# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests
