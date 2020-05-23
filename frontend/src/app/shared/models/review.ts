import {User} from './user';
import {Verdict} from './verdict';
import {Submission} from './submission';

export interface Review {
  id: string;
  submission: Submission;
  user: User;
  verdict: Verdict;
  recommendation: string;
}
