/**
 * Walmart has 100+ stores and each store has multiple associate
 *
 * In a store, an associate can work for multiple departments. Here are shifts of a associate in various departments:
 * In hardware department: From 8 to 10
 * In customer support department: From 10 to 12
 * In Diary department: From 14 to 21
 *
 * Given the above split of shifts, provide an API/method to return the different shifts of a colleague for the day after merging contiguous shifts.
 * This will be exposed to the colleague in different UI and help them plan their day accordingly.
 * Output: shift timings in this case are 8 to 12 and 14 to 21.

1: 8 - 10, 10 -12
2: 14 - 21

Department:
    name
    startTime
    endTime


method:
    - contiguousShifts({{8, 10}, {10, 12}, {14, 21}})  => {{8, 12}, {14, 21}}

test cases:
    - null -> {}
    - {} -> {}
    - {{}} -> {}
    - {{}, {8, 10}} -> {{ 8, 10}}
    - {{8, 10}}  => {{8, 10}}
    - {{8, 10}, {8, 10}}  => {{8, 10}}
    - {{8, 10}, {10, 12}, {14, 21}}  => {{8, 12}, {14, 21}}
    - {{8, 10}, {9, 12}, {11, 21}}  => {{8, 21}}
    - limited data set -> 
 */