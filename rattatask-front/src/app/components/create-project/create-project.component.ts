import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { ProjectService } from '../../services/project.service';
import { catchError, of } from 'rxjs';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-create-project',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './create-project.component.html',
  styleUrl: './create-project.component.css',
})
export class CreateProjectComponent {
  readonly dialog = inject(MatDialog);

  openDialog(
    enterAnimationDuration: string,
    exitAnimationDuration: string
  ): void {
    this.dialog.open(DialogCreateProject, {
      enterAnimationDuration,
      exitAnimationDuration,
    });
  }
}

@Component({
  selector: 'dialog-animations-example-dialog',
  templateUrl: 'dialog-create-project.html',
  imports: [
    MatButtonModule,
    MatDialogActions,
    MatDialogClose,
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatInputModule,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  standalone: true,
})
export class DialogCreateProject {
  constructor(private projectService: ProjectService) {}
  readonly dialogRef = inject(MatDialogRef<DialogCreateProject>);
  form: FormGroup = new FormGroup({
    name: new FormControl(''),
  });

  onSubmit(): void {
    if (this.form.valid) {
      this.projectService.postProject(this.form.value.name).subscribe(() => {
        catchError((error) => {
          console.log(error);
          return of(false);
        });
      });
      location.reload();
    }
  }
}
